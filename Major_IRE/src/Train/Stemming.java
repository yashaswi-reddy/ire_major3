package Train;


import java.io.IOException;

public class Stemming {
	
    public String stemword(String s)
    {
        PorterStemmerAlgo pa = new PorterStemmerAlgo();
        String s1 = pa.step1(s);
        String s2 = pa.step2(s1);
        String s3= pa.step3(s2);
        String s4= pa.step4(s3);
        String s5= pa.step5(s4);
        return s5;
    }
  
}