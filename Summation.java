/**
 * This class is designed to calculate the sum of integers
 * @author provided  by Dr. Elfouly, modified by Taline Mkrtschjan
 * Due 14 April 2021
 */
class Summation implements Runnable{
    
    private int upper;
    private int lower;
    private Sum sumValue;
    
    public Summation(int lower, int upper, Sum sumValue) {
        
        this.lower = lower;
        this.upper = upper;
        this.sumValue = sumValue;
    }
    
    /**
     * This method add the sum of integers
     */
    public void run() {
        double sum = 0;
        for (int i = lower; i <= upper; i++) {
            sum +=i; 
        }
        sumValue.setSum(sum);
    }
}
