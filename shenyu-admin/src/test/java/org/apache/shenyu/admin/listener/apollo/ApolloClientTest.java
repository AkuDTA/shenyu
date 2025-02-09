/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.admin.listener.apollo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.shenyu.admin.config.properties.ApolloProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ApolloProperties.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApolloClientTest {

    @MockBean
    private ApolloClient apolloClient;

    /**
     * Method under test: {@link ApolloClient#ApolloClient(ApolloProperties)}.
     */
    @Test
    public void testConstructor() {
        ApolloProperties apolloConfig = new ApolloProperties();
        apolloConfig.setAppId("42");
        apolloConfig.setClusterName("Cluster Name");
        apolloConfig.setEnv("Env");
        apolloConfig.setMeta("Meta");
        apolloConfig.setNamespace("Namespace");
        apolloConfig.setPortalUrl("http://localhost:8080");
        apolloConfig.setToken("ABC123");
        new ApolloClient(apolloConfig);
        assertEquals("42", apolloConfig.getAppId());
        assertEquals("ABC123", apolloConfig.getToken());
        assertEquals("http://localhost:8080", apolloConfig.getPortalUrl());
        assertEquals("Namespace", apolloConfig.getNamespace());
        assertEquals("Meta", apolloConfig.getMeta());
        assertEquals("Env", apolloConfig.getEnv());
        assertEquals("Cluster Name", apolloConfig.getClusterName());
    }

    /**
     * Method under test: {@link ApolloClient#getItemValue(String)}.
     */
    @Test
    public void testGetItemValue() {
        when(apolloClient.getItemValue(Mockito.<String>any())).thenReturn("42");
        assertEquals("42", apolloClient.getItemValue("Key"));
        verify(apolloClient).getItemValue(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ApolloClient#createOrUpdateItem(String, Object, String)}.
     */
    @Test
    public void testCreateOrUpdateItem() {
        doNothing().when(apolloClient)
                .createOrUpdateItem(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<String>any());
        apolloClient.createOrUpdateItem("Key", (Object) "Value", "Comment");
        verify(apolloClient).createOrUpdateItem(Mockito.<String>any(), Mockito.<Object>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ApolloClient#publishNamespace(String, String)}.
     */
    @Test
    public void testPublishNamespace() {
        doNothing().when(apolloClient).publishNamespace(Mockito.<String>any(), Mockito.<String>any());
        apolloClient.publishNamespace("Dr", "1.0.2");
        verify(apolloClient).publishNamespace(Mockito.<String>any(), Mockito.<String>any());
    }
}

