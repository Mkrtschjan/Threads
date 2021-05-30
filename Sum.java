/**
 * This class is designed to return a sum
 * @author provided  by Dr. Elfouly, modified by Taline Mkrtschjan
 * Due 14 April 2021
 */
public class Sum {
    
    private double sum;
    
    /**
     * This method returns the sum
     * @return sum
     */
    public double getSum() {
        return sum;
    }
    
    /**
     * This method sets the sum
     * @param sum
     */
    public synchronized void setSum(double sum) {
        this.sum = sum;
    }    
}
