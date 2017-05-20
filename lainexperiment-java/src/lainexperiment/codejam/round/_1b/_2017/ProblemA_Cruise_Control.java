/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 22/04/2017
 *
 * Code jam 2017
 * Problem: Problem A. Steed 2: Cruise Control
 * Status: accepted
 * 
 * Problem
 * 
 * Annie is a bus driver with a high-stress job. She 
 * tried to unwind by going on a Caribbean cruise, but 
 * that also turned out to be stressful, so she has 
 * recently taken up horseback riding.
 * 
 * Today, Annie is riding her horse to the east along a 
 * long and narrow one-way road that runs west to east. 
 * She is currently at kilometer 0 of the road, and her 
 * destination is at kilometer D; kilometers along the 
 * road are numbered from west to east.
 * 
 * There are N other horses traveling east on the same 
 * road; all of them will go on traveling forever, and 
 * all of them are currently between Annie's horse and 
 * her destination. The i-th of these horses is initially 
 * at kilometer Ki and is traveling at its maximum speed 
 * of Si kilometers per hour.
 * 
 * Horses are very polite, and a horse H1 will not pass 
 * (move ahead of) another horse H2 that started off 
 * ahead of H1. (Two or more horses can share the same 
 * position for any amount of time; you may consider the 
 * horses to be single points.) Horses (other than 
 * Annie's) travel at their maximum speeds, except that 
 * whenever a horse H1 catches up to another slower horse 
 * H2, H1 reduces its speed to match the speed of H2.
 * 
 * Annie's horse, on the other hand, does not have a
 * maximum speed and can travel at any speed that Annie 
 * chooses, as long as it does not pass another horse. To 
 * ensure a smooth ride for her and her horse, Annie wants 
 * to choose a single constant "cruise control" speed for 
 * her horse for the entire trip, from her current position 
 * to the destination, such that her horse will not pass 
 * any other horses. What is the maximum such speed that 
 * she can choose? 
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test 
 * cases, T; T test cases follow. Each test case begins with 
 * two integers D and N: the destination position of all of 
 * the horses (in kilometers) and the number of other horses 
 * on the road. Then, N lines follow. The i-th of those lines 
 * has two integers Ki and Si: the initial position (in 
 * kilometers) and maximum speed (in kilometers per hour) of 
 * the i-th of the other horses on the road.
 * 
 * Output Format
 * 
 * For each test case, output one line containing Case #x: 
 * y, where x is the test case number (starting from 1) and 
 * y is the maximum constant speed (in kilometers per hour) 
 * that Annie can use without colliding with other horses. 
 * y will be considered correct if it is within an absolute 
 * or relative error of 10-6 of the correct answer. 
 * 
 * Input
 * 
3
2525 1
2400 5
300 2
120 60
60 90
100 2
80 100
70 10
 * 
 * Output
 * 
Case #1: 101.000000
Case #2: 100.000000
Case #3: 33.333333
 * 
 */
package lainexperiment.codejam.round._1b._2017;

import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProblemA_Cruise_Control {
    
    public static void main(String[] args) throws IOException {
        String inputFile = ProblemA_Cruise_Control.class.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(Paths.get("/tmp/in")): 
            new Scanner(ProblemB_Stable_Neighbors.class.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            double D = scanner.nextLong();
            double maxTime = range(0, scanner.nextInt())
                .mapToDouble(j -> (D - scanner.nextLong()) / scanner.nextLong())
                .max()
                .getAsDouble();
            double r = D / maxTime;
            //System.out.println(r);
            System.out.format("Case #%d: %s\n", i + 1, BigDecimal.valueOf(r).setScale(6, RoundingMode.HALF_UP));
        }
        scanner.close();
    }

}
