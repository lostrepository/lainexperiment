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
 * Problem: Braces
 * Status: accepted
 *
 * Problem
 *
 * Print if string contains balanced parenthesis or not.
 *
 * Sample
 *
 * Input
{}[]()
 *
 * Output
YES
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

bool isBalanced(const string& str) {
    stack<char> s;
    string::const_iterator i = str.begin();
    while (i != str.end()) {

        char ch = *i++;
        if (ch == '[' || ch == '{' || ch == '(') {
            s.push(ch);
            continue;
        }
        if (s.empty()) return false;
        char sch = s.top();
        s.pop();
        if ((sch == '[' && ch == ']') ||
                (sch == '{' && ch == '}') ||
                (sch == '(' && ch == ')'))
            continue;
        return false;
    }
    return s.empty();
}

vector<string> Braces(vector<string> v) {
    vector<string> res;
    for (vector<string>::const_iterator i = v.begin(); i != v.end(); ++i)
        res.push_back(isBalanced(*i)? "YES": "NO");
    return res;
}

int main() {
    vector<string> l;
    l.push_back("{}[]()");
    l.push_back("{[}]");
    l.push_back("[{)]");
    l = Braces(l);
    for (vector<string>::const_iterator i = l.begin(); i != l.end(); ++i)
        cout << *i << endl;
    return 0;
}
