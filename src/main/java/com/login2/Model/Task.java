package com.login2.Model;

import java.util.ArrayList;
import java.util.List;

public class Task extends Thread{
  public final Integer num;
  public List<Integer> primenumbers;
  private static Integer maxnumber=Integer.MIN_VALUE;
  private static List<Integer> maxprimenumbers=new ArrayList<>();

  public Task(Integer num, List<Integer> primenumbers) {
    this.num = num;
    this.primenumbers=primenumbers;
  }

  public void run() {

    if (num < maxnumber) {
      if(2<num)
        primenumbers.add(2);
      for (Integer number : maxprimenumbers) {
        if (number > num)
          break;
        primenumbers.add(number);
      }
    }
    else {
      if (2 == num) {
        primenumbers.add(2);                                  //since loop starts from integer 3,base case is done here
        return;
      }
      if (2 < num) {                                        //if number greater than 2,add 2 to prime numbers list
        primenumbers.add(2);
      }
      int a = 2;
      if(!(maxprimenumbers.isEmpty())){
      for(Integer integer:maxprimenumbers) {
        primenumbers.add(integer);
        a = integer.intValue();
      }
      }

      for (int i = a+1; i <= num; i++) {
        if (isprime(i)) {
          primenumbers.add(i);
          maxprimenumbers.add(i);
        }
      }
      synchronized (this) {
        maxnumber = num;
      }
    }
  }

  private boolean isprime(int i){
    if (0==(i%2))                                         //if number is itself even,avoid entering into the loop
      return false;
    else{
      for (int k=3;k<=Math.sqrt(i);k++)                   //reducing the loop till square root of number
      {
        if(0==(i%k))
          return false;
      }
    }
    return true;
  }

}
