/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.associations.Apriori;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.AttributeSelection;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * @author toryzhou
 * @since 2019-07-13
 */
public class HorseColicDataMinTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HorseColicDataMinTest.class);

    @Test
    public void test() throws Exception {
        AttributeSelection as = new AttributeSelection();
        ASSearch asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "2", "-N", "3"});
        as.setSearch(asSearch);
        ASEvaluation asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
        as.setEvaluator(asEval);
        Instances instances = new Instances(read());
        as.SelectAttributes(instances);
        instances = as.reduceDimensionality(instances);
        Classifier classifier = AbstractClassifier.forName("weka.classifiers.functions.MultilayerPerceptron", new String[]{"-L", "0.5317334404209181", "-M", "0.8985883131646849", "-H", "i", "-C", "-R", "-D", "-S", "1"});
        classifier.buildClassifier(instances);

        double sum = instances.numInstances();////测试语料实例数
        double right = 0.0f;
        //测试分类结果
        for (int i = 0; i < sum; i++) {
            double v = classifier.classifyInstance(instances.instance(i));
            System.out.println(v);
            double v1 = instances.instance(i).classValue();
            System.out.println(v1);
            //如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
            if (v == v1) {
                right++;//正确值加1
            }

        }

        System.out.println("J48 classification precision:" + (right / sum));
    }


    private BufferedReader read() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("arff/horseColicData_min.arff"), "UTF-8"));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return reader;
    }

    /**
     * The training data gathered so far.
     */
    private Instances instances = null;

    @Test
    public void calc() throws Exception {
        ArrayList<Attribute> list = new ArrayList<>();


//        Attribute attribute = new Attribute("a", Arrays.asList("1"), 1);
//        Attribute attributeB = new Attribute("b", Arrays.asList("2"), 1);
//        list.add(attribute);
//        list.add(attributeB);
//        Instances instances = new Instances("test", list, 2);


        String nameOfDataset = "mylogic";

        // Create vector of attributes.
        ArrayList<Attribute> attributes = new ArrayList<Attribute>(2);

        // Add attribute for holding messages.
        attributes.add(new Attribute("A"));
        attributes.add(new Attribute("B"));

        // Add class attribute.
//        ArrayList<String> classValues = new ArrayList<String>(2);
//        classValues.add("miss");
//        classValues.add("hit");
//        attributes.add(new Attribute("Class", classValues));

        // Create dataset with initial capacity of 100, and set index of class.
        instances = new Instances(nameOfDataset, attributes, 100);

        instances.setClassIndex(instances.numAttributes() - 1);

        Instance instance = makeInstance("1", instances);
        instance.setClassValue(0.8);
        System.out.println(instance.classValue());

        Instance instance2 = makeInstance("2", instances);
        instance2.setClassValue(0.9);
        instances.add(instance);
        instances.add(instance2);

        System.out.println(instances.size());
        System.out.println(instances.attribute("A"));
        System.out.println(instances.attribute("A").value(0));
        System.out.println(instances.attribute("A").value(1));
        System.out.println(instances.attribute("A").value(2));


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
        double right = 0.0f;
        //测试分类结果
        for (int i = 0; i < sum; i++) {
            double v = classifier.classifyInstance(instances.instance(i));
            System.out.println(v);
            double v1 = instances.instance(i).classValue();
            System.out.println(v1);
        }
    }

    private Instance makeInstance(String text, Instances data) {
        // Create instance of length two.
        Instance instance = new DenseInstance(2);

        // Set value for message attribute
        Attribute messageAtt = data.attribute("A");
        instance.setValue(messageAtt, messageAtt.addStringValue(text));
//        instance.setClassValue("hit");
        // Give instance access to attribute information from the dataset.
        instance.setDataset(data);

        return instance;
    }

    @Test
    @Deprecated
    public void testAprioriOutput() throws Exception {
        // load data
        Instances data = ConverterUtils.DataSource.read("arff/horseColicData_min.arff");
        data.setClassIndex(data.numAttributes() - 1);

        // build associator
        Apriori apriori = new Apriori();
        apriori.setClassIndex(data.classIndex());
        apriori.buildAssociations(data);

        // output associator
        System.out.println(apriori);

    }

    @Test
    @Deprecated
    public void stringToReader() throws IOException {
        Reader reader = null;
        BufferedReader r = new BufferedReader(reader);
        StringBuilder b = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            b.append(line);
            b.append("\r\n");
        }
        b.toString();
    }

}
