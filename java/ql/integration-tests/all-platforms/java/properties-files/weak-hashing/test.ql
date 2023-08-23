import java
import semmle.code.java.security.WeakHashingAlgorithmPropertyQuery

query predicate weakAlgorithmUse(DataFlow::Node sink) {
  exists(DataFlow::Node source | InsecureAlgorithmPropertyFlow::flowPath(source, sink))
}
