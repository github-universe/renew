/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.weka;

import com.patent.renew.dto.CompanyMixedPojo;
import com.patent.renew.dto.CompanyPojo;
import com.patent.renew.dto.CompanyStatisticsPojo;
import com.patent.renew.entity.CompanyMixedTraining;
import com.patent.renew.entity.CompanyStatisticsTraining;
import com.patent.renew.entity.CompanyTraining;
import com.patent.renew.service.DataNormalizeService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.AttributeSelection;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author toryzhou
 * @since 2019-07-12
 */
@Component
public class WekaClient implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(WekaClient.class);
    public static final String RENEW_FIELD = "renew";
    public static final String IGNORED_ID = "id";

    @Autowired
    private DataNormalizeService normalizeService;

    private Classifier companyBaseClassifier;

    private Classifier companyStatisticsClassifier;

    private Classifier companyMixedLrClassifier;
    private Classifier companyMixedRfClassifier;


    public double predictByCompanyBase(CompanyPojo companyPojo) throws Exception {

        String nameOfDataSet = "base_test_set";
        Class<?> cls = Class.forName("com.patent.renew.entity.CompanyTraining");
        Instances baseInstances = makeInstances(nameOfDataSet, cls);
        Instance toPredictInstance = makeBaseInstance(baseInstances, companyPojo);
        baseInstances.add(toPredictInstance);
        double value = companyBaseClassifier.classifyInstance(toPredictInstance);
        LOGGER.info("Predict base info : {} , value is {}.", companyPojo, value);
        return value;

    }

    public double predictByCompanyStatistics(CompanyStatisticsPojo statisticsPojo) throws Exception {
        String nameOfDataSet = "statistics_test_set";
        Class<?> cls = Class.forName("com.patent.renew.entity.CompanyStatisticsTraining");
        Instances statisticsInstances = makeInstances(nameOfDataSet, cls);

        Instance toPredictInstance = makeStatisticsInstance(statisticsInstances, statisticsPojo);
        double value = companyStatisticsClassifier.classifyInstance(toPredictInstance);
        LOGGER.info("Predict statistics  info : {} , value is {}.", statisticsPojo, value);
        return value;
    }


    public double predictByMixedStatistics(CompanyMixedPojo mixedPojo) throws Exception {
        String nameOfDataSet = "mixed_test_set";
        Class<?> cls = Class.forName("com.patent.renew.entity.CompanyMixedTraining");
        Instances mixedInstances = makeInstances(nameOfDataSet, cls, true);
        Instance toPredictInstance = makeMixedInstance(mixedInstances, mixedPojo);
        mixedInstances.add(toPredictInstance);
        double lRValue = companyMixedLrClassifier.classifyInstance(toPredictInstance);


        Instances mixedRfInstances = makeInstances(nameOfDataSet, cls);
        Instance toRfPredictInstance = makeMixedInstance(mixedRfInstances, mixedPojo);
        mixedRfInstances.add(toRfPredictInstance);
        double rFValue = companyMixedRfClassifier.classifyInstance(toRfPredictInstance);
        LOGGER.info("Predict mixed info : {} , LR value is {}, RF value is {}.", mixedPojo, lRValue, rFValue);
        return lRValue;
    }

    private Instances makeInstances(final String nameOfDataset, Class clazz, boolean isClassString) throws ClassNotFoundException {

        Field[] fields = clazz.getDeclaredFields();
        ArrayList<Attribute> attributes = new ArrayList<>(fields.length);
        Arrays.asList(fields).stream().forEach(field -> {
            // Set value for message attribute
            String fieldName = field.getName();
            if (!StringUtils.equals(IGNORED_ID, fieldName)) {

                if (StringUtils.equals(RENEW_FIELD, fieldName)) {
                    attributes.add(new Attribute(fieldName, isClassString));
                } else {
                    attributes.add(new Attribute(fieldName));
                }
            }
        });

        return new Instances(nameOfDataset, attributes, 100);
    }

    private Instances makeInstances(final String nameOfDataset, Class clazz) throws ClassNotFoundException {
        return makeInstances(nameOfDataset, clazz, false);
    }

    private Instance makeBaseInstance(Instances instances, CompanyPojo companyPojo) {
        CompanyTraining companyTraining = normalizeService.normalizeCompanyData(companyPojo);
        Field[] fields = companyTraining.getClass().getDeclaredFields();
        // Create instance of length fields.
        Instance instance = new DenseInstance(fields.length - 1);

        Arrays.asList(fields).stream().forEach(field -> {
            // Set value for message attribute
            field.setAccessible(true);
            String fieldName = field.getName();
            Attribute attribute = instances.attribute(fieldName);

            try {
                if (!StringUtils.equals(IGNORED_ID, fieldName)) {
                    if (StringUtils.equals(RENEW_FIELD, fieldName)) {
                        instance.setValue(attribute, Double.valueOf((String) field.get(companyTraining)));
                    } else {
                        instance.setValue(attribute, (Integer) field.get(companyTraining));
                    }
                }
            } catch (IllegalAccessException e) {
                LOGGER.warn("Make base instance error with field: {}", fieldName);
            }
        });

        // Give instance access to attribute information from the dataset.
        instance.setDataset(instances);

        return instance;
    }

    private Instance makeStatisticsInstance(Instances instances, CompanyStatisticsPojo statisticsPojo) {
        CompanyStatisticsTraining statisticsTraining = normalizeService.normalizeCompanyStatisticsData(statisticsPojo);
        Field[] fields = statisticsTraining.getClass().getDeclaredFields();
        // Create instance of length fields.
        Instance instance = new DenseInstance(fields.length - 1);

        Arrays.asList(fields).stream().forEach(field -> {
            // Set value for message attribute
            field.setAccessible(true);
            String fieldName = field.getName();
            Attribute attribute = instances.attribute(fieldName);

            try {
                if (!StringUtils.equals(IGNORED_ID, fieldName)) {
                    if (StringUtils.equals(RENEW_FIELD, fieldName)) {
                        instance.setValue(attribute, Integer.valueOf((String) field.get(statisticsTraining)));
                    } else {
                        instance.setValue(attribute, (Integer) field.get(statisticsTraining));
                    }
                }

            } catch (IllegalAccessException e) {
                LOGGER.warn("Make statistics instance error with field: {}", fieldName);
            }
        });

        // Give instance access to attribute information from the dataset.
        instance.setDataset(instances);

        return instance;
    }

    private Instance makeMixedInstance(Instances instances, CompanyMixedPojo mixedPojo) {
        CompanyMixedTraining mixedTraining = normalizeService.normalizeCompanyMixedData(mixedPojo);
        Field[] fields = mixedTraining.getClass().getDeclaredFields();
        // Create instance of length fields.
        Instance instance = new DenseInstance(fields.length - 1);

        Arrays.asList(fields).stream().forEach(field -> {
            // Set value for message attribute
            field.setAccessible(true);
            String fieldName = field.getName();


            try {
                if (!StringUtils.equals(IGNORED_ID, fieldName)) {
                    if (StringUtils.equals(RENEW_FIELD, fieldName)) {
                        LOGGER.info("Make mixed instance with field: {}, value: {}", fieldName, Integer.valueOf((String) field.get(mixedTraining)));
                        Attribute attribute = instances.attribute(fieldName);
                        instances.setClass(attribute);
                        instance.setValue(attribute, (String) field.get(mixedTraining));
                    } else {
                        LOGGER.info("Make mixed instance with field: {}, value: {}", fieldName, field.get(mixedTraining));
                        Attribute attribute = instances.attribute(fieldName);
                        instance.setValue(attribute, (Integer) field.get(mixedTraining));
                    }
                }

            } catch (Exception e) {
                LOGGER.warn("Make mixed instance error with field: {}", fieldName, e);
            }
        });

        // Give instance access to attribute information from the dataset.
        instance.setDataset(instances);

        return instance;
    }

    public void calc() throws Exception {
        LOGGER.info("Begin calculate the prediction");
        AttributeSelection as = new AttributeSelection();
        ASSearch asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "2", "-N", "3"});
        as.setSearch(asSearch);
        ASEvaluation asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
        as.setEvaluator(asEval);

        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("arff/horseColicData_min.arff"), "UTF-8"));
        Instances instances = new Instances(reader);
        as.SelectAttributes(instances);
        instances = as.reduceDimensionality(instances);
        Classifier classifier = AbstractClassifier.forName("weka.classifiers.functions.MultilayerPerceptron", new String[]{"-L", "0.5317334404209181", "-M", "0.8985883131646849", "-H", "i", "-C", "-R", "-D", "-S", "1"});
        classifier.buildClassifier(instances);


        double sum = instances.numInstances();////测试语料实例数
        double right = 0.0f;
        //测试分类结果
        for (int i = 0; i < sum; i++) {

            double v = classifier.classifyInstance(instances.instance(i));
            double v1 = instances.instance(i).classValue();
            //如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
            if (v == v1) {
                right++;//正确值加1
            }

        }
        System.out.println("J48 classification precision:" + (right / sum));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Load base Model
//        ClassPathResource companyBaseModel = new ClassPathResource("model/company_base_JRIP.model");
        ClassPathResource companyBaseModel = new ClassPathResource("model/company_training.model");
        companyBaseClassifier = (Classifier) SerializationHelper.read(companyBaseModel.getInputStream());

        // Load statistics Model
        ClassPathResource companyStatisticsModel = new ClassPathResource("model/company_statistics.model");
        companyStatisticsClassifier = (Classifier) SerializationHelper.read(companyStatisticsModel.getInputStream());

        // Load mixed Model
        ClassPathResource companyLrMixedModel = new ClassPathResource("model/company_mixed_LR.model");
        companyMixedLrClassifier = (Classifier) SerializationHelper.read(companyLrMixedModel.getInputStream());

        ClassPathResource companyMixedRfModel = new ClassPathResource("model/company_mixed_RF.model");
        companyMixedRfClassifier = (Classifier) SerializationHelper.read(companyMixedRfModel.getInputStream());
//        ClassPathResource companyMixedModel = new ClassPathResource("model/company_mixed.model");
        ClassPathResource companyMixedModel = new ClassPathResource("model/company_mixed_training.model");
        companyMixedClassifier = (Classifier) SerializationHelper.read(companyMixedModel.getInputStream());
    }
}
