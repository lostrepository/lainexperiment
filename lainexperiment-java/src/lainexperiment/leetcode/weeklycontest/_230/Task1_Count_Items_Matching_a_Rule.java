/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._230;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 27/02/2021
 * 
 * Problem: Count Items Matching a Rule
 * Status: accepted
 * 
 * You are given an array items, where each items[i] = [typei, colori, namei]
 * describes the type, color, and name of the ith item. You are also given a
 * rule represented by two strings, ruleKey and ruleValue.
 * 
 * The ith item is said to match the rule if one of the following is true:
 * 
 * - ruleKey == "type" and ruleValue == typei.
 * - ruleKey == "color" and ruleValue == colori.
 * - ruleKey == "name" and ruleValue == namei.
 * 
 * Return the number of items that match the given rule.
 *
 * Input
 * 
items = [
  ["phone","blue","pixel"],
  ["computer","silver","lenovo"],
  ["phone","gold","iphone"]],
 ruleKey = "color", ruleValue = "silver"
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task1_Count_Items_Matching_a_Rule {
    
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        return (int)items.stream()
            .filter(l -> matches(l, ruleKey, ruleValue))
            .count();
    }

    private boolean matches(List<String> l, String ruleKey, String ruleValue) {
        switch (ruleKey) {
        case "type": return l.get(0).equals(ruleValue);
        case "color": return l.get(1).equals(ruleValue);
        case "name": return l.get(2).equals(ruleValue);
        }
        throw new RuntimeException();
    }

    @Test
    public void test() {
        assertEquals(1, countMatches(List.of(
            List.of("phone","blue","pixel"),
            List.of("computer","silver","lenovo"),
            List.of("phone","gold","iphone")), "color", "silver"));
        assertEquals(2, countMatches(List.of(
            List.of("phone","blue","pixel"),
            List.of("computer","silver","phone"),
            List.of("phone","gold","iphone")), "type", "phone"));
        assertEquals(1, countMatches(List.of(
            List.of("", "red", ""),
            List.of("", "", "red")), "color", "red"));
    }
}
