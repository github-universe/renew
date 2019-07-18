/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent;

import com.patent.renew.dto.CompanyPojo;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author toryzhou
 * @since 2019-07-14
 */
public class RenewTest {


    @Test
    public void calc() throws Exception {
//        Instances instances = getInstances();
        Instances instances = getHorseInstances();

        System.out.println("instances.size()=" + instances.size());
        System.out.println(instances.attribute("A"));


        AttributeSelection as = new AttributeSelection();
        ASSearch asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "2", "-N", "3"});
        as.setSearch(asSearch);
        ASEvaluation asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
        as.setEvaluator(asEval);
        as.SelectAttributes(instances);
        instances = as.reduceDimensionality(instances);
        Classifier classifier = AbstractClassifier.forName("weka.classifiers.functions.MultilayerPerceptron", new String[]{"-L", "0.5317334404209181", "-M", "0.8985883131646849", "-H", "i", "-C", "-R", "-D", "-S", "1"});
        classifier.buildClassifier(instances);
        double sum = instances.numInstances();////测试语料实例数
        //测试分类结果
        for (int i = 0; i < sum; i++) {
            double v = classifier.classifyInstance(instances.instance(i));
            double[] doubles = classifier.distributionForInstance(instances.instance(i));
            System.out.println("distributionForInstance:" + doubles);

            System.out.println(v);
            double v1 = instances.instance(i).classValue();
            System.out.println(v1);
        }
    }

    @Test
    public void sickHousePrediction() throws Exception {
        // Load Model
        ClassPathResource rfModel = new ClassPathResource("model/sickHourse.model");
        Classifier clf = (Classifier) SerializationHelper.read(rfModel.getInputStream());
        // Load test data
        ClassPathResource testDataResource = new ClassPathResource("arff/horseColicData_test.arff");
        InputStream testDataResourceInputStream = testDataResource.getInputStream();
        Instances testSet = new Instances(new BufferedReader(new InputStreamReader(testDataResourceInputStream)));
        double pResult = clf.classifyInstance(testSet.firstInstance());
        double nResult = clf.classifyInstance(testSet.lastInstance());
        System.out.println("Predict p result:" + pResult + ", n result: "+ nResult);
    }

    private Instances getInstances() {
        String nameOfDataset = "renew";
        // Create vector of attributes.
        ArrayList<Attribute> attributes = new ArrayList<>(2);
        // Add attribute for holding messages.
        attributes.add(new Attribute("A"));
        attributes.add(new Attribute("B"));

        // Create dataset with initial capacity of 100, and set index of class.
        Instances instances = new Instances(nameOfDataset, attributes, 100);
        Instance instance = makeInstance(instances, 2, 3);
        Instance instance2 = makeInstance(instances, 1, 5);
        instances.add(instance);
        instances.add(instance2);
        return instances;
    }

    private Instance makeInstance(Instances data, double a, double b) {
        // Create instance of length two.
        Instance instance = new DenseInstance(2);

        // Set value for message attribute
        Attribute attributeA = data.attribute("A");
        instance.setValue(attributeA, a);

        Attribute attributeB = data.attribute("B");
        instance.setValue(attributeB, b);

        // Give instance access to attribute information from the dataset.
        instance.setDataset(data);

        return instance;
    }


    // horseColicData_min.arff 这个文件的最后一列是0和1, 那么是否是前面数值和1 那条记录接近结果就约接近1
    private Instances getHorseInstances() {

        String nameOfDataset = "renew";
        ArrayList<Attribute> attributes = new ArrayList<>(22);
        attributes.add(new Attribute("A"));
        attributes.add(new Attribute("B"));
        attributes.add(new Attribute("C"));
        attributes.add(new Attribute("D"));
        attributes.add(new Attribute("E"));
        attributes.add(new Attribute("F"));
        attributes.add(new Attribute("G"));
        attributes.add(new Attribute("H"));
        attributes.add(new Attribute("I"));
        attributes.add(new Attribute("J"));
        attributes.add(new Attribute("K"));
        attributes.add(new Attribute("L"));
        attributes.add(new Attribute("M"));
        attributes.add(new Attribute("N"));
        attributes.add(new Attribute("O"));
        attributes.add(new Attribute("P"));
        attributes.add(new Attribute("Q"));
        attributes.add(new Attribute("R"));
        attributes.add(new Attribute("S"));
        attributes.add(new Attribute("T"));
        attributes.add(new Attribute("U"));
        attributes.add(new Attribute("Z"));

        Instances instances = new Instances(nameOfDataset, attributes, 100);

        Instance instance = new DenseInstance(22);

        instance.setValue(instances.attribute("A"), 1);
        instance.setValue(instances.attribute("B"), 1);
        instance.setValue(instances.attribute("C"), 38.1);
        instance.setValue(instances.attribute("D"), 60);
        instance.setValue(instances.attribute("E"), 12);
        instance.setValue(instances.attribute("F"), 3);
        instance.setValue(instances.attribute("G"), 3);
        instance.setValue(instances.attribute("H"), 3);
        instance.setValue(instances.attribute("I"), 1);
        instance.setValue(instances.attribute("J"), 0);
        instance.setValue(instances.attribute("K"), 4);
        instance.setValue(instances.attribute("L"), 3);
        instance.setValue(instances.attribute("M"), 3);
        instance.setValue(instances.attribute("N"), 2);
        instance.setValue(instances.attribute("O"), 2);
        instance.setValue(instances.attribute("P"), 0);
        instance.setValue(instances.attribute("Q"), 0);
        instance.setValue(instances.attribute("R"), 51);
        instance.setValue(instances.attribute("S"), 65);
        instance.setValue(instances.attribute("T"), 0);
        instance.setValue(instances.attribute("U"), 0);
        instance.setValue(instances.attribute("Z"), 1);

        instance.setDataset(instances);

        instances.add(instance);

        return instances;
    }

    @Test
    public void testBeanMap() {
        CompanyPojo companyPojo = new CompanyPojo();
        BeanMap beanMap = new BeanMap(companyPojo);
        Set<Object> keySet = beanMap.keySet();
        String nameOfDataset = "renew";
        ArrayList<Attribute> attributes = new ArrayList<>(keySet.size() - 1);
        for (Object key : keySet) {
            if (!StringUtils.equals("class", String.valueOf(key))) {
                attributes.add(new Attribute((String) key));
            }
        }
        Assert.assertTrue(attributes.size() > 0);
    }
}
