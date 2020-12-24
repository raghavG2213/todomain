package com.example.todomain.view

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPSize.INDEX
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.action.update.UpdateHelper.ContextFields.INDEX
import org.springframework.beans.factory.annotation.Autowired
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.index.query.QueryBuilders

import java.io.IOException
import java.util.*

class TodoViewService {

    @Autowired
    lateinit var transportClient: TransportClient

    private val objectMapper = ObjectMapper()

    fun search(value: String?): List<TodoView>? {
        if (!transportClient.admin().indices().prepareExists(TodoView().INDEX).execute().actionGet().isExists) {
                return emptyList()
            }
        val response: SearchResponse? = transportClient.prepareSearch(TodoView().INDEX)
//                ?.setTypes(TodoView().TYPE)
                ?.setQuery(QueryBuilders.termQuery("_all", value))
                ?.get()
        val result: MutableList<TodoView> = ArrayList()
        if (response != null) {
            for (searchHit in response.hits) {
                try {
                    result.add(objectMapper.readValue(searchHit.getSourceAsString(), TodoView::class.java))
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
            }
        }
        return result
    }

    fun index(todoView: TodoView) {
        try {
            val ir: IndexResponse? = transportClient
                    .prepareIndex(TodoView().INDEX, TodoView().TYPE, todoView.id)
                    ?.setSource(objectMapper.writeValueAsString(todoView), XContentType.JSON)
                    ?.get()
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun remove(id: String?) {
        transportClient.prepareDelete(TodoView().INDEX, TodoView().TYPE, id)?.get()
    }
}
