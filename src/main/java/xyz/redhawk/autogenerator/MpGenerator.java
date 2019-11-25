package xyz.redhawk.autogenerator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author redhawk<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/11/20 16:07 <br>
 * @see xyz.redhawk.autogenerator <br>
 */
public class MpGenerator {

    //这里是生成代码的位置
    private static String systemDir = "d://cr-sso-01";

    //项目中包的根路径, 你只要更改一下包的名称; 就可以了;
    private static String PACKAGE_PATH = "xyz.redhawk.kapok";

    //生成class 作者姓名
    private static String BYNAME = "redhawk";


    // 数据库配置, 我分开来;这里比较好双击; 那些需要经常修改;的都分开来;好双击
    private static String DATABASES_PATH_PREFIX = "jdbc:mysql://" + "localhost" + ":";
    private static String DATABASES_PATH_PORT = "3306" + "/";
    private static String DATABASES_PATH_NAME = "cr-sso";
    private static String DATABASES_PATH_SUFFIX = "?characterEncoding=utf8";
    private static String DATABASES_URL = DATABASES_PATH_PREFIX + DATABASES_PATH_PORT + DATABASES_PATH_NAME + DATABASES_PATH_SUFFIX;
    // private static String DATABASES_URL = "jdbc:mysql://localhost:3306/cr_sso?characterEncoding=utf8";
    private static String DATABASES_DRIVER = "com.mysql.jdbc.Driver";
    private static String DATABASES_USERNAME = "root";
    private static String DATABASES_PASSWORD = "root";

    // 是否继承BaseEntity
    private static boolean BASE_ENTITY = true;

    // 自定义实体父类
    private static String SUPER_ENTITY_CLASS = PACKAGE_PATH + ".objects.BaseEntity";

    // xml 生成路径
    private static String MYBATIS_XML = systemDir + "/src/main/resources/mybatis/";
    // entity 生成路径
    private static String ENTITY_PATH = PACKAGE_PATH + ".objects.entity";

    // mapper 生成路径
    private static String MAPPER_PATH = PACKAGE_PATH + ".mapper";

    //通用Controller BaseController 地址
    private static String BASE_CONTROLLER_PATH = PACKAGE_PATH + ".common.BaseController";
    //通用Service BaseService 地址
    private static String BASE_SERVICE_PATH = PACKAGE_PATH + ".common.BaseService";
    //通用分页工具类 地址
    private static String PAGE_UTILS_PATH = PACKAGE_PATH + ".common.PageUtils";
    //通用Page类 地址
    private static String PAGE_PATH = PACKAGE_PATH + ".common.BasePage";





    public static void main(String[] args) {
        // 生成test表
        String[] tabArr = new String[]{"sys_permission"};
        // 模块名称
        String moduleName = "ehr";
        // 是否继承BaseEntity
        BASE_ENTITY = false;

        execute(moduleName, tabArr);
    }

    private static void execute(String moduleName, String[] tabArr) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = "/Users/syk/Documents/*/*/";
        gc.setOutputDir(systemDir + "/src/main/java");
        gc.setAuthor(BYNAME);
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //gc.setControllerName("SSSSScontroller");
        // 是否覆盖已有文件
        gc.setFileOverride(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(DATABASES_DRIVER);
        dsc.setUsername(DATABASES_USERNAME);
        dsc.setPassword(DATABASES_PASSWORD);
        dsc.setUrl(DATABASES_URL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(null);
        // 这个地址是生成的配置文件的包路径
        pc.setEntity(ENTITY_PATH);

        //pc.setController("com.cikers.ps.controller");
        pc.setMapper(MAPPER_PATH);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //这里是自定义模板统一父类
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        //String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return MYBATIS_XML + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

//		 //配置自定义输出模板
        // 不需要其他的类型时，直接设置为null就不会成对应的模版了
        //templateConfig.setEntity("...");
        templateConfig.setService(null);
        templateConfig.setController(null);
        templateConfig.setServiceImpl(null);
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也
        // 可以自定义模板名称 只要放到目录下，名字不变 就会采用这个模版 下面这句有没有无所谓
        // 模版去github上看地址：
        /**https://github.com/baomidou/mybatis-plus/tree/3.0/mybatis-plus-generator/src/main/resources/templates*/
        //templateConfig.setEntity("/templates/entity.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setLogicDeleteFieldName("del_flag");
        strategy.setRestControllerStyle(true);
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        if (BASE_ENTITY) {
            strategy.setSuperEntityClass(SUPER_ENTITY_CLASS);
            // 自定义实体，公共字段
            strategy.setSuperEntityColumns(new String[]{"create_by", "create_time", "update_by", "update_time", "del_flag", "remark"});
        }

        //   strategy.setSuperMapperClass("com.cikers.ps.util.IMapper");
        strategy.setEntityLombokModel(false);
        //strategy.setRestControllerStyle(false);
        //strategy.setSuperControllerClass("com.cikers.ps.controller.MysqlController");
        strategy.setInclude(tabArr);
        // 设置继承的父类字段,// 自定义实体，公共字段
        strategy.setSuperEntityColumns("id", "modifiedBy", "modifiedOn", "createdBy", "createdOn");
        //strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();



    }
}