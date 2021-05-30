/** This class is designed to calculate the sum of integers using threads
 *
 * @author Taline Mkrtschjan
 * Due 14 April 2021
 */
public class Driver {
    private static long startTime, startTime1, startTime2, startTime3;
    private static long endTime, endTime1, endTime2, endTime3;
    private static long processTime, processTime1, processTime2, processTime3;
    private static final String[] N = {"100000000"};
    
    public static void main(String[] args) {

        //Assigning the String array to args
        args = N;
         
        //To execute only if array has elements
        if (args.length > 0) {
            if (Integer.parseInt(args[0]) < 0) {
                System.err.println("The integer " + args[0] + " must be >= 0.");
            }
            //Tp parse the string into an integer
            else {
                System.err.println("\nCalculating the sum of integers from 0 to " 
                                                                     + args[0]);
                System.err.println();
                
                Sum sumObject = new Sum();
                int upper = Integer.parseInt(args[0]);
                int lower = 0;                
                
                //create a new thread and calculate sum of N integers
                Thread thrd = new Thread(new Summation(lower, upper, sumObject));
                
                //Start thread
                startTime = System.currentTimeMillis();
                thrd.start();
                
                try{
                    //end thread and timer
                    thrd.join();

                } catch (InterruptedException ie) {}  
                
                //End timer
                endTime = System.currentTimeMillis();
                processTime = endTime - startTime;
                
                Sum sumObject1 = new Sum();
                int lower1 = 0; 
                int upper1 = upper/2;
                
                //To create a new thread and calculate sum of integers from 0 to N/2
                Thread thrd1 = new Thread(new Summation(lower1, upper1, sumObject1));
                
                //Run 2 threads sequentially and time them
                //Start new thread and timer
                startTime1 = System.currentTimeMillis();
                thrd1.start();
                
                try {
                    //end new thread
                    thrd1.join();
                
                }catch (InterruptedException ie) {}
                
                //End timer
                endTime1 = System.currentTimeMillis();
                processTime1 = endTime1 - startTime1;
            
                Sum sumObject2 = new Sum();
                int lower2 = (upper/2)+1; 
                int upper2 = upper;
                
                //To create a new thread and calculate sum of integers from (N/2)+1 to N
                Thread thrd2 = new Thread(new Summation(lower2, upper2, sumObject2));
                startTime2 = System.currentTimeMillis();
                thrd2.start();
                
                try {
                    //end new thread
                    thrd2.join();
                    
                }catch (InterruptedException ie) {}
                
                //End timer
                endTime2 = System.currentTimeMillis();
                processTime2 = endTime2 - startTime2;
            
                
                Sum sumObject3 = new Sum();
                int lower3 = 0; 
                int upper3 = upper/2;

                //To create a thread to calculate sum of integers from 0 to N/2
                Thread thrd3 = new Thread(new Summation(lower3, upper3, sumObject3));
                
                Sum sumObject4 = new Sum();
                int lower4 = (upper/2)+1; 
                int upper4 = upper;
                
                //To create a thread to calculate sum of integers from (N/2)+1 to N
                Thread thrd4 = new Thread(new Summation(lower4, upper4, sumObject4));
                
                //Run 2 threads (thread 3 and 4) concurrently and start timer
                startTime3 = System.currentTimeMillis();
                thrd3.start();
                thrd4.start();
                
                try{
                    //End threads 3 and 4 along with timer
                    thrd3.join();
                    thrd4.join();

                } catch (InterruptedException ie) {}    
                
                //End timer
                endTime3 = System.currentTimeMillis();
                processTime3 = endTime3 - startTime3;

                //Output results of sum of integers from 0 to N calculated in 
                //one thread
                System.err.println("Using 1 thread, the total sum "
                                 + "of integers from " + lower + " to " + upper
                                 + "\nis = " + sumObject.getSum() + " calculated "
                                 + "in " + (processTime) + " milliseconds.\n");
                
                //Output results of sum of integers from 0 to N/2 and from (N/2)+1
                //to N in 2 sequentially running threads 
                System.err.println("Using 2 sequential threads, the total sum "
                                 + "of integers from " + lower1 + " to " + upper1
                                 + "\nand " + lower2 + " to " + upper2 + " is = "
                                 + (sumObject1.getSum() + sumObject2.getSum()) 
                                 + " calculated in " + (processTime1 
                                 + processTime2) + " milliseconds.\n");

                //Output results of sum of integers from 0 to N/2 and from (N/2)+1
                //to N in 2 concurrently running threads 
                System.err.println("Using 2 concurrent threads, the total sum "
                                 + "of integers from " + lower3 + " to " + upper3 
                                 + "\nand " + lower4 + " to " + upper4 + " is = " 
                                 + (sumObject3.getSum() + sumObject4.getSum()) 
                                 + " calculated in only " + (processTime3) 
                                 + " milliseconds.\n");
                
                //Output timer results
                System.err.println("Conclusion\n==========");
                if ((processTime1 + processTime2) < processTime || 
                     processTime3 < processTime) {
                    
                    //If timer of two thread is less than timer of one thread in
                    //the case of larger numbers
                    System.err.println("When we divide a large integer into 2 "
                                     + "and calculate the sum of integers in 2"
                                     + "\nconcurrently running threads, we "
                                     + "get a more efficient run of the program"
                                     + "\nsince the total processing time of the "
                                     + "integer divided into 2 threads is less\n"
                                     + "than the total processing time of the "
                                     + "same calculation in just one thread.");
                }
                //If timer of one thread is equal to timer of 2 threads in the 
                //case of smaller numbers
                else if ((processTime1 + processTime2) == processTime && 
                     (processTime3 == processTime)) {
                    System.err.println("With smaller integers, when we divide "
                                     + "the integer into 2, and calculate\n"
                                     + "the sum of integers in 2 sequential or "
                                     + "concurrent threads, we might get\nan equal "
                                     + "efficiency run time of the program.");
                }
                //If timer of one thread is less than timer of 2 threads in the 
                //case of smaller numbers
                else {
                    System.err.println("When we divide a small integer into 2, "
                                     + "and calculate the sum of integers in 2\n"
                                     + "sequential or concurrent threads, it "
                                     + "might seem that the 2 threads result in "
                                     + "a\nless efficient time than the total "
                                     + "processing time of the same "
                                     + "calculation in\njust one thread.");
                }
                 System.err.println();
            }
        }
        else {
            //To print error message if program does not run
            System.err.println("Error usage: Summation of " + args[0]);
        }
    }
}