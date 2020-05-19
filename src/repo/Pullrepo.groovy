// src/org/foo/Zot.groovy
package repo;

def checkOutFrom(repo) {
    git url: "https://github.com/YonathanGuez/${repo}.git"
}

return this