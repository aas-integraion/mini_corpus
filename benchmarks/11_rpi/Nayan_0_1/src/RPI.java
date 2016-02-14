
/**
 * Write a description of class Magicka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class RPI
{
  public static void main(String args[])throws Exception
  {
      
             Scanner sc=new Scanner(new File("A-large.in"));
             
             FileOutputStream out=new FileOutputStream("oprpi.txt");
             PrintWriter fout=new PrintWriter(out);
             
             int t=Integer.parseInt(sc.nextLine());
             
      for(int tests=1;tests<=t;tests++)                
      {       
            // System.out.println("ENTER");
            //   System.out.println("CASE:--- "+tests);           
             String newline=sc.nextLine();
             StringTokenizer strtok=new StringTokenizer(newline,"\t ");
                
             int n=Integer.parseInt(strtok.nextToken());
             int arr[][]=new int[n][n];
                
              int games[]=new int[n];
             for(int i=0;i<n;i++)
             {  
                 String rec=sc.nextLine();
                // strtok=new StringTokenizer(rec,"\t ");
                
                 for(int j=0;j<n;j++)
                 {
                     char c=rec.charAt(j);
                     int val=0;
                     if(c=='.') arr[i][j]=-1;
                                         
                     else{
                         arr[i][j]=(int)(c-'0');
                         games[i]++;
                        }
                    //  System.out.print(arr[i][j]);  
                 }//System.out.println();  
             }
             
             double wp[]=new double[n];
             for(int i=0;i<n;i++)
             {  int total=0,win=0;
                 for(int j=0;j<n;j++)
                 {
                     if(arr[i][j]!=-1)
                     {
                         total++;
                         if(arr[i][j]==1) win++;
                     }
                  }
                  wp[i]=win/(double)total;
             //     System.out.print(wp[i]+",");
                }
                
                double owp[]=new double[n];
             //    System.out.println("OWPS");
                for(int i=0;i<n;i++)
                { 
                    int total=0;
                    double g=0;
                    for(int j=0;j<n;j++)
                    {
                        if(arr[i][j]!=-1)
                        {   total++;
                            g+= (wp[j]*games[j] - arr[j][i])/(double)(games[j]-1);
//                            System.out.println(g+" for "+(games[j]-1));
                        }              
                    }
                  
                  owp[i]=g/total;
              //     System.out.println(owp[i] +"--,");
                }
                
                double oowp[]=new double[n];
                for(int i=0;i<n;i++)
                { 
                    int total=0;
                    double g=0;
                    for(int j=0;j<n;j++)
                    {
                        if(arr[i][j]!=-1)
                        {   total++;
                            g+=owp[j];
                        }              
                    }
                  
                  oowp[i]=g/total;
                }
                  
                double rpi[]=new double[n];
                fout.println("Case #"+tests+":");
                 for(int i=0;i<n;i++)
                { 
                   rpi[i]= 0.25 * wp[i] + 0.50 * owp[i] + 0.25 * oowp[i];
                   fout.println(rpi[i]);
                }             
                                
                
        }//tests   
        fout.close();
        out.close();
     }//main
 }//class

    


