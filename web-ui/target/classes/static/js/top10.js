eval(function (p, a, c, k, e, r) {
    e = function (c) {
        return c.toString(a)
    };
    if (!''.replace(/^/, String)) {
        while (c--) r[e(c)] = k[c] || e(c);
        k = [function (e) {
            return r[e]
        }];
        e = function () {
            return '\\w+'
        };
        c = 1
    }
    ;
    while (c--) if (k[c]) p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
    return p
}('6(0.a){}3{$("#4").5(\'2\',\'//7.8.9/1/\'+0.b+\'.1\')}', 12, 12, 'MacPlayer|html|src|else|install|attr|if|union|maccms|com|Status|PlayFrom'.split('|'), 0, {}));