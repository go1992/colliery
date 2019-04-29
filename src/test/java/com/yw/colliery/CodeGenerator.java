package com.yw.colliery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //swagger2
        gc.setSwagger2(true);
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("jobob");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/coal_mine?serverTimezone=Hongkong&autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        // dsc.setSchemaName("public");
        //com.mysql.cj.jdbc.Driver
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("colliery");
        pc.setParent("com.yw");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.yw.colliery.entity.BaseEntity");
//        strategy.setSuperEntityColumns("id");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("com.yw.colliery.absys.BaseController");
        //排除表
        strategy.setExclude("");
//        strategy.setInclude(
//        		//地测信息管理-水文地质钻孔排查
//        		"dc_swdz_zkpc"
//        		//地测信息管理-水文险情及预警处理
//        		,"dc_swxq_yjcl"
//        		//地测信息管理-基本参数-矿区位置
//        		,"dc_jbcs_kqwz"
//        		//地测信息管理-基本参数-用户
//        		,"dc_jbcs_yh"
//        		//安全风险管控-年度风险辨识
//        		,"aqfx_ndfx"
//        		//安全风险管控-管控措施修改记录
//        		,"aqfx_csxg"
//        		//安全风险管控-管控效果及分析
//        		,"aqfx_gkxg"
//        		//安全风险管控-风险跟踪及报表
//        		,"aqfx_fxgz"
//        		//安全风险管控-领导跟踪检查
//        		,"aqfx_ldgz"
//        		//安全风险管控-基本参数设置-矿领导设置
//        		,"aqfx_jbcs_kld"
//        		//安全风险管控-基本参数设置-风险类型设置
//        		,"aqfx_jbcs_fxlx"
//        		//隐患排查治理-隐患录入
//        		,"yhpc_yhlr"
//        		//隐患排查治理-安全预警信息发布
//        		,"yhpc_yjxx"
//        		//隐患排查治理-隐患罚款条款设置
//        		,"yhpc_fktk"
//        		//隐患排查治理-隐患类型设置
//        		,"yhpc_yhlx"
//        		//调度信息管理-基本信息设置-生产单位设置
//        		,"ddxx_jb_scdw"
//        		//调度信息管理-安全生产调度报表
//        		,"ddxx_scdd"
//        		//调度信息管理-生产安全事故处理追踪
//        		,"ddxx_scsg"
//        		);
        //strategy.setInclude("dc_swxq_yjcl");

        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}