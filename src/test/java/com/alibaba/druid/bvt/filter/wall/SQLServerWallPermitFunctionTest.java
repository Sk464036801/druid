/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.bvt.filter.wall;

import junit.framework.TestCase;

import org.junit.Assert;

import com.alibaba.druid.wall.WallUtils;

/**
 * SQLServerWallPermitFunctionTest
 *
 * @author RaymondXiu
 * @version 1.0, 2012-3-18
 * @see
 */
public class SQLServerWallPermitFunctionTest extends TestCase {

    /**
     * @param name
     */
    public SQLServerWallPermitFunctionTest(String name) {
        super(name);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void test01() throws Exception {
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT user_name();"));
    }
    
    public final void test_sp_addsrvrolemenber() throws Exception {
    	Assert.assertFalse(WallUtils.isValidateSqlServer("EXEC master.dbo.sp_addsrvrolemember ‘user’, ‘sysadmin"));
    }
    
    public final void test_sp_helpdb() throws Exception {
        Assert.assertFalse(WallUtils.isValidateSqlServer("EXEC sp_helpdb master"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("EXEC sp_helpdb pubs"));
    }
    
    public final void test_sp_droplogin() throws Exception {
    	Assert.assertFalse(WallUtils.isValidateSqlServer("EXEC sp_droplogin ‘user’;"));
    }
    public final void test_sp_addlogin() throws Exception {
    	Assert.assertFalse(WallUtils.isValidateSqlServer("EXEC sp_addlogin ‘user’, ‘pass’; "));
    }
    
    public final void test_db_name() throws Exception {
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT DB_NAME()"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT DB_NAME(0); "));
    }
    
    public final void test_host_name() throws Exception {
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT HOST_NAME()"));
    }
    
    /**
     * 测试权限结构
     * @throws Exception
     */
    public void test_is_srvrolemember() throws Exception {
    	Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘sysadmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘dbcreator’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘bulkadmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘diskadmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘processadmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘serveradmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘setupadmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘securityadmin’);"));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘sysadmin’); "));
        Assert.assertFalse(WallUtils.isValidateSqlServer("SELECT is_srvrolemember(‘sysadmin’, ‘sa’); "));
    }


}
