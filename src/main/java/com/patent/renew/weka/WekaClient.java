/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.weka;

import com.patent.renew.dto.CompanyModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author toryzhou
 * @since 2019-07-12
 */
@Component
public class WekaClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WekaClient.class);


    public double predict(CompanyModel companyModel) throws Exception {
        String nameOfDataset = "renew";
        ArrayList<Attribute> attributes = new ArrayList<>(2);
        attributes.add(new Attribute("accountNumber"));
        attributes.add(new Attribute("ipLogin"));
        Instances instances = new Instances(nameOfDataset, attributes, 100);

        Instance instance = makeInstance(instances, companyModel);
        instances.add(instance);
        AttributeSelection as = new AttributeSelection();
        ASSearch asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "2", "-N", "3"});
        as.setSearch(asSearch);
        ASEvaluation asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
        as.setEvaluator(asEval);
        as.SelectAttributes(instances);
        instances = as.reduceDimensionality(instances);
        Classifier classifier = AbstractClassifier.forName("weka.classifiers.meta.RandomCommittee", new String[]{"-I", "8", "-S", "1", "-W", "weka.classifiers.trees.RandomForest", "--", "-I", "7", "-K", "0", "-depth", "0"});
        classifier.buildClassifier(instances);
        double value = classifier.classifyInstance(instances.instance(0));

        // todo 怎么通过模型得出预测值
        return value;

    }

    private Instance makeInstance(Instances instances, CompanyModel companyModel) {

        // Create instance of length two.
        Instance instance = new DenseInstance(2);

        // Set value for message attribute
        Attribute attributeA = instances.attribute("accountNumber");
        attributeA.setWeight(1);
        instance.setValue(attributeA, companyModel.getAccountNumber());

        Attribute attributeB = instances.attribute("ipLogin");
        attributeB.setWeight(1);
        instance.setValue(attributeB, companyModel.getIpLogin());

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

}
