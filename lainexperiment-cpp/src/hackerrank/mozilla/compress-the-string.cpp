/*
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 *
 * Date: 20/03/2016
 *
 * Hacker rank
 * Mozilla challenge
 * Problem: Compress the string
 * Status: accepted
 *
 * Problem
 *
 * Compress the string using run-length algorithm.
 *
 * Sample
 *
 * Input
aaee
 *
 * Output
a2e2
 *
 */

#include <assert.h>
#include <list>
#include <vector>
#include <stack>
#include <iostream>
#include <sstream>
#include <iterator>

using namespace std;

string compress(string str) {
    if (str.empty()) return "";
    ostringstream res;
    string::const_iterator i = str.begin();
    char prev = *i;
    int cnt = 0;
    while (i != str.end()) {
        char ch = *i++;
        if (prev == ch) {
            cnt++;
            continue;
        }
        res << prev;
        prev = ch;
        if (cnt > 1)
            res << cnt;
        cnt = 1;
    }
    if (cnt > 0)
        res << prev;
    if (cnt > 1)
        res << cnt;
    return res.str();
}

int main() {
    cout << compress("aaee") << endl;
}
