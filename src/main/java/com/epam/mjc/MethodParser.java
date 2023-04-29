package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String accessModifier = null;
        String returnType = null;
        String methodName = null;

        String[] arrayString = signatureString.split("\\(")[0].split(" ");

        int openParenPosition = signatureString.indexOf("(");
        int closeParenPosition = signatureString.indexOf(")");
        String arg = signatureString.substring(openParenPosition + 1, closeParenPosition);

        if (arrayString.length == 3) {

            accessModifier = arrayString[0];
            returnType = arrayString[1];
            methodName = arrayString[2];

            if (arg.equals("")) {

                MethodSignature methodSignature = new MethodSignature(methodName);
                methodSignature.setAccessModifier(accessModifier);
                methodSignature.setReturnType(returnType);

                return methodSignature;

            } else {

                String[] keyValue = arg.split(", ");

                List <MethodSignature.Argument> arguments = new ArrayList<>();

                for(String kV : keyValue) {
                    MethodSignature.Argument argument = new MethodSignature.Argument(kV.split(" ")[0], kV.split(" ")[1]);
                    arguments.add(argument);
                }

                MethodSignature methodSignature = new MethodSignature(methodName, arguments);
                methodSignature.setAccessModifier(accessModifier);
                methodSignature.setReturnType(returnType);

                return methodSignature;
            }
        } else if(arrayString.length == 2) {

            returnType = arrayString[0];

            methodName = arrayString[1];

            String[] keyValue = arg.split(", ");

            List <MethodSignature.Argument> arguments = new ArrayList<>();

            for(String kV : keyValue) {
                MethodSignature.Argument argument = new MethodSignature.Argument(kV.split(" ")[0], kV.split(" ")[1]);
                arguments.add(argument);
            }

            MethodSignature methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setReturnType(returnType);

            return methodSignature;
        } else {
            throw new UnsupportedOperationException("You should implement this method.");
        }

    }
}
