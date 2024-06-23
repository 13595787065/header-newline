package com.atnewline;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date ${DATE} ${TIME}
 * @Created by xj
 */

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    //添加mybatis-plus的拦截器插件
    @Bean
    public MybatisPlusInterceptor interceptor(){
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        //分页插件
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //乐观锁
        plusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //禁止全表更改和删除
        plusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return plusInterceptor;
    }
}