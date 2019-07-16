/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent;

import org.junit.Test;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;


import weka.classifiers.Classifier;
import weka.core.Utils;
import weka.core.WekaPackageManager;
import weka.core.packageManagement.Package;

import java.util.List;
import java.util.Random;

/**
 * @author toryzhou
 * @since 2019-07-14
 */
public class NoteBookTest {

    @Test
    public void test() throws Exception {

        Instances data = ConverterUtils.DataSource.read("https://svn.cms.waikato.ac.nz/svn/weka/trunk/wekadocs/data/labor.arff");
        data.setClassIndex(data.numAttributes() - 1);

        J48 cls = new J48();
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(cls, data, 10, new Random(1));
        System.out.println(eval.toSummaryString());
    }

    @Test
    public void usingPackage() throws Exception {
        WekaPackageManager.loadPackages(false);

        boolean gridSearchInstalled = false;
        List<Package> installedPackages = WekaPackageManager.getInstalledPackages();
        for (Package p: installedPackages) {
            if (p.getName().equals("gridSearch")) {
                gridSearchInstalled = true;
                break;
            }
        }
        if (!gridSearchInstalled)
            throw new IllegalStateException("Please install the gridSearch package first!");


        Classifier gridSearch = (Classifier) Utils.forName(Classifier.class, "weka.classifiers.meta.GridSearch", new String[0]);
        System.out.println(Utils.toCommandLine(gridSearch));
    }
}
