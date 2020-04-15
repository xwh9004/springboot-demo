package com.example.data.es;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * <p><b>Description:</b>
 * RestHighLevelClient 提供的ElasticSearch 操作集合
 * <p><b>Company:</b>
 *<see>https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-indices-exists.html</see>
 * @author created by Jesse Hsu at 13:48 on 2020/4/15
 * @version V0.1
 * @classNmae ESUtils
 */
@Component
public class ElasticSearchInstance {

    @Autowired
    RestHighLevelClient client;

    /**
     * query index named indexName whether existed
     * this method is synchronous and the client waits for the boolean to be returned
     * before continuing with code execution
     * @param indexName
     * @return
     * @throws IOException
     */
    public boolean indexExists(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
//        request.local(false);
//        request.humanReadable(true);
//        request.includeDefaults(false);
//        request.indicesOptions(indicesOptions);
        return   client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     *  query index name indexName whether existed via asynchronous
     * @param indexName
     * @param listener
     * @throws IOException
     */
    public void indexExistsAsync(String indexName, ActionListener<Boolean> listener) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        client.indices().existsAsync(request, RequestOptions.DEFAULT,listener);
    }

    /**
     * create index and maybe you want to specified custom  settings
     * @param indexName
     * @param builder
     * @return
     * @throws IOException
     */
    public boolean createIndex(String indexName, Settings.Builder builder) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        if(null!=builder){
            request.settings(builder);
        }
       return createIndex(request);
    }

    public boolean createIndex(String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);

        return createIndex(request);
    }

    public boolean createIndexAndSetAlias(String indexName, String alias) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.alias(new Alias(alias));
        return createIndex(request);
    }


    private boolean createIndex(CreateIndexRequest request) throws IOException {
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }
    /**
     * create index and specific mapping
     * @param indexName
     * @param mapping
     * @param builder
     * @return
     * @throws IOException
     */
    public boolean createIndex(String indexName, Map<String, Object> mapping, Settings.Builder builder) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        if(null!=builder){
            request.settings(builder);
        }
        request.mapping(mapping);
        return createIndex(request);
    }

    /**
     * GET index mapping
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
     * GET index mapping
     * @param index
     * @param listner
     * @throws IOException
     */
    public void getMappingAsync(String index, ActionListener<GetMappingsResponse> listner) throws IOException {
        GetMappingsRequest request = new GetMappingsRequest();
        request.indices(index);
        client.indices().getMappingAsync(request ,RequestOptions.DEFAULT,listner);

    }
}
