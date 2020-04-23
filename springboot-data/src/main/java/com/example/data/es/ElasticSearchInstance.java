package com.example.data.es;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * <p><b>Description:</b>
 * RestHighLevelClient 提供的ElasticSearch 操作集合
 * <p><b>Company:</b>
 * <see>https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-indices-exists.html</see>
 *
 * @author created by Jesse Hsu at 13:48 on 2020/4/15
 * @version V0.1
 * @classNmae ESUtils
 */
@Component
public class ElasticSearchInstance {

    @Autowired
    RestHighLevelClient client;


    /**
     * 同步获取 索引的setting信息
     *
     * @param index
     * @return
     * @throws IOException
     */
    public GetSettingsResponse getSettings(String index) throws IOException {
        GetSettingsRequest request = new GetSettingsRequest().indices(index);
        request.humanReadable(true);
        GetSettingsResponse response = client.indices().getSettings(request, RequestOptions.DEFAULT);

        return response;
    }

    /**
     * query index  whether existed
     * this method is synchronous and the client waits for the boolean to be returned
     * before continuing with code execution
     *
     * @param index
     * @return
     * @throws IOException
     */
    public boolean indexExists(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
//        request.local(false);
//        request.humanReadable(true);
//        request.includeDefaults(false);
//        request.indicesOptions(indicesOptions);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * query index  whether existed via asynchronous
     *
     * @param index
     * @param listener
     * @throws IOException
     */
    public void indexExistsAsync(String index, ActionListener<Boolean> listener) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        client.indices().existsAsync(request, RequestOptions.DEFAULT, listener);
    }

    /**
     * create index and maybe you want to specified custom  settings
     *
     * @param index
     * @param builder
     * @return
     * @throws IOException
     */
    public boolean createIndex(String index, Settings.Builder builder) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        if (null != builder) {
            request.settings(builder);
        }
        return createIndex(request);
    }

    public boolean createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);

        return createIndex(request);
    }

    public boolean createIndexAndSetAlias(String index, String alias) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.alias(new Alias(alias));
        return createIndex(request);
    }


    private boolean createIndex(CreateIndexRequest request) throws IOException {
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    /**
     * create index and specific mapping
     *
     * @param indexName
     * @param mapping
     * @param builder
     * @return
     * @throws IOException
     */
    public boolean createIndex(String indexName, Map<String, Object> mapping, Settings.Builder builder) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        if (null != builder) {
            request.settings(builder);
        }
        request.mapping(mapping);
        return createIndex(request);
    }

    /**
     * GET index mapping
     *
     * @param index
     * @throws IOException
     */
    public Map<String, Object> getMapping(String index) throws IOException {
        GetMappingsRequest request = new GetMappingsRequest();
        request.indices(index);
        GetMappingsResponse getMappingResponse = client.indices().getMapping(request, RequestOptions.DEFAULT);
        Map<String, MappingMetaData> allMappings = getMappingResponse.mappings();
        MappingMetaData indexMapping = allMappings.get(index);
        return indexMapping.sourceAsMap();
    }

    /**
     * GET index mapping async
     *
     * @param index
     * @param listener
     * @throws IOException
     */
    public void getMappingAsync(String index, ActionListener<GetMappingsResponse> listener) throws IOException {
        GetMappingsRequest request = new GetMappingsRequest();
        request.indices(index);
        client.indices().getMappingAsync(request, RequestOptions.DEFAULT, listener);
    }

    public SearchResponse search(String index,String field,String term) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(field, term));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        return response;
    }

}
