package org.example.listeners;
import bsh.util.NameCompletionTable;
import org.example.annotationsFramework.TagGroups;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class TestNGFilterListener implements IMethodInterceptor {
        private List<IMethodInstance> filteredMethods = new ArrayList<>();

        @Override
        public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

            int initialSize = methods.size();
            filteredMethods.addAll(methods);

            long startTime = System.currentTimeMillis();
            System.out.println(startTime);


            for (int i = 0; i<initialSize; i++ ) {
                if(methods.get(i).getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(TagGroups.class)) {
                    TagGroups tagManager = methods.get(i).getMethod().getConstructorOrMethod().getMethod().getAnnotation(TagGroups.class);
                    String[] tagList = tagManager.tags();
                    if ((Arrays.stream(tagList).anyMatch((tag) -> tag.contains("smoke")))) {
                        filteredMethods.remove(methods.get(i));
                    }else
                        System.out.println("remove this");
                }
                else {
                   break;
                }
            }

            methods.removeAll(filteredMethods);
            long endTime = System.currentTimeMillis();
            System.out.println(endTime);
            System.out.println((endTime-startTime));
            return methods;
        }
    }

