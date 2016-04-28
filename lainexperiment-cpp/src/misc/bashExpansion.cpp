/*
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 *
 * Date: 03/04/2016
 *
 * Problem
 *
 * Implement bash expansion.
 *
 * Sample
 *
 * Input
(aa(b,c)d(ef,gh)j)
 *
 * Output
aabdefj,aabdghj,aacdefj,aacdghj
 *
 */

#include <assert.h>
#include <list>
#include <vector>
#include <memory>
#include <stack>
#include <iostream>
#include <sstream>
#include <utility>
#include <iterator>
#include <algorithm>

using namespace std;

struct Node;

typedef shared_ptr<Node> node_t;
typedef list<node_t> list_t;
typedef list<node_t>::iterator iter_t;

struct Node {
    list_t adj;
    char ch = 0;
};

list_t empty;
unsigned int p = 0;

list_t bashExpansion(const string& str, const list_t& in) {
    if (p == str.length()) return empty;
    list_t curIn(in);
    list_t out;
    while (p != str.length()) {
        char ch = str.at(p);
        p++;
        if (ch == '(') {
            list_t&& t = bashExpansion(str, curIn);
            curIn.assign(t.begin(), t.end());
            continue;
        }
        if (ch == ')') {
            copy(curIn.begin(), curIn.end(), back_inserter(out));
            break;
        }
        if (ch == ',') {
            copy(curIn.begin(), curIn.end(), back_inserter(out));
            curIn.assign(in.begin(), in.end());
            continue;
        }
        node_t v(new Node);
        v->ch = ch;
        for_each(curIn.begin(), curIn.end(), [&](node_t u){
            u->adj.push_back(v);
        });
        curIn.clear();
        curIn.push_back(v);
    }
    return out;
}

void dfs(list_t g, const string& str, string& out) {
    if (g.empty()) {
        out += str + ",";
        return;
    }
    for (iter_t i = g.begin(); i != g.end(); ++i) {
        dfs((*i)->adj, str + (*i)->ch, out);
    }
}

string bashExpansion(const string& in) {
    string res;
    p = 0;
    list_t g{node_t(new Node)};
    bashExpansion(in, g);
    dfs(g.front()->adj, "", res);
    return res;
}

int main() {
    assert("hh," == bashExpansion("(hh)"));
    assert("hha,hhb," == bashExpansion("(hh(a,b))"));
    assert("aabdefj,aabdghj,aacdefj,aacdghj," == bashExpansion("(aa(b,c)d(ef,gh)j)"));
    return 0;
}
