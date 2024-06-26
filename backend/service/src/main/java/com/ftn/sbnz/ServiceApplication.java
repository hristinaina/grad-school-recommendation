package com.ftn.sbnz;

import java.util.Arrays;

import com.ftn.sbnz.model.repo.GradProgramRepo;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ServiceApplication {

    @Autowired
    private GradProgramRepo gradProgramRepo;

    private static Logger log = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ServiceApplication.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("Application beans:\n");
        for (String beanName : beanNames) {
            sb.append(beanName + "\n");
        }
        log.info(sb.toString());
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1000);
        return kContainer;
    }

    @Bean
    public KieSession fwKsession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession("fwKsession");


    }

    @Bean
    public KieSession bwKsession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        return kContainer.newKieSession("bwKsession");
    }

    /*
     * KieServices ks = KieServices.Factory.get(); KieContainer kContainer =
     * ks.newKieContainer(ks.newReleaseId("drools-spring-v2",
     * "drools-spring-v2-kjar", "0.0.1-SNAPSHOT")); KieScanner kScanner =
     * ks.newKieScanner(kContainer); kScanner.start(10_000); KieSession kSession =
     * kContainer.newKieSession();
     */
}
