package ca.jrvs.apps.practice;

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        System.out.println("\nTesting regex program...");
        RegexExcImp regexExcImp = new RegexExcImp();
        String fileName = "abc.jpeg";
        boolean isJpeg = regexExcImp.matchJpeg(fileName);
        System.out.println("The filename has the jpg or jpeg extension: "+isJpeg);
        String ip = "001.122.456.999";
        boolean isValidIp = regexExcImp.matchIp(ip);
        System.out.println("The ip variable is a valid ip: "+isValidIp);
        String line = " ";
        Boolean isEmptyLine = regexExcImp.isEmptyLine(line);
        System.out.println("The line variable is a empty string: "+isEmptyLine);
    }
}