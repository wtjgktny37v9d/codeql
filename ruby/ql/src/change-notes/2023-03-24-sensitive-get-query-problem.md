---
category: minorAnalysis
---
* `rb/sensitive-get-query` no longer reports flow paths from input parameters to sensitive use nodes. This avoids cases where many flow paths could be generated for a single parameter, which caused excessive paths to be generated.
