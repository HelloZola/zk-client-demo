package com.client.nameserver;

import com.config.ConfigConstants;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkLock;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class DemeTest {

    private static ZkClient zkClient = null;

    @Before
    public void before() {
        zkClient = new ZkClient(ConfigConstants.zkServerPath, 60000, 60000, new SerializableSerializer());
    }


    @Test
    public void test() {
        System.out.println("hello");
    }

    @Test
    public void nameserverTest() {

        ZkClient client = new ZkClient(ConfigConstants.zkServerPath, 5000, 5000, new BytesPushThroughSerializer());
    }

    /**
     * 创建节点
     */
    @Test
    public void createNodeTest() {

//        ZkClient zkClient = new ZkClient(ConfigConstants.zkServerPath, 10000, 10000, new SerializableSerializer());
        System.out.println("conneted ok!");

        User user = new User();
        user.setId("1");
        user.setName("testUser");

        /**
         * "/testUserNode" :节点的地址
         * user：数据的对象
         * CreateMode.PERSISTENT：创建的节点类型
         */
        String createPath = "/testUserNode" + new Date().getTime();
        String path = zkClient.create(createPath, user, CreateMode.PERSISTENT_SEQUENTIAL);
        //输出创建节点的路径
        System.out.println("createPath:" + path);
        System.out.println("created path:" + path);
    }

    @Test
    public void lockTets() {
        try {
            zkClient.createEphemeral("hhh");
            System.out.println("done");
        } catch (Exception e) {
            System.out.println("not done");
        }
    }

    @Test
    public void tryLock() {
        try {
//            zkClient.createEphemeral(lockPath);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            concurrentHashMap.put("", "hh");
            System.out.println(concurrentHashMap.get(""));
        } catch (Exception e) {

        }
    }


}
