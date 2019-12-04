package com.talent.developer;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

public class TestActiviti {

    //获取流程引擎,创建数据库
    ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

    //自动加载classpath下的activiti.cfg.xml
    //        ProcessEngine proEng = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void aVoid(){
        System.out.println("get engin"+processEngine);
        System.out.println("===========");
    }
    @Test
    public void deployFlow() {
//        InputStream  is =this.getClass().getClassLoader().getResourceAsStream("diagrams");
//        ZipInputStream zipInputStream = new ZipInputStream(is);
        //获取仓库服务实例
        RepositoryService rs = processEngine.getRepositoryService();
        //创建部署构建实例
        DeploymentBuilder builder = rs.createDeployment();
        //添加工作流
        builder.addClasspathResource("diagrams/actTest.bpmn");//bpmn文件的名称
        builder.name("流程定义");
//        builder.addZipInputStream(zipInputStream);
        //部署
        Deployment deployment = builder.deploy();

        System.out.println(deployment.getName());
    }
    @Test
    public void startFlow() {
        //使用runtimeservic启动流程实例,正在执行的流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcessAx");//

        System.out.println("流程实例id: " + pi.getId()); //2501
        System.out.println("流程定义id: " + pi.getProcessDefinitionId()); //myProcessAx:1:4

    }
    @Test
    public void getCurrentPersonalTask() {
        List<Task> list = processEngine.getTaskService()//正在执行的任务
                .createTaskQuery() //创建任务查询对象
                .taskAssignee("閼颁礁绗�鐎癸拷") //指定个人任务查询，指定查询人 //act_ru_task
                .listPage(0,10);
                //.list();
        if (list!=null&&list.size()>0){
            for (Task task : list){
                System.out.println(task.getId());
                System.out.println(task.getName());
                System.out.println(task.getAssignee());
            }
        }
    }
    @Test
    public void completePersonalTask() {
        String taskId = "5002"; //2504 5002
        processEngine.getTaskService().complete(taskId);
        System.out.println("########complete task ,");
    }
}
