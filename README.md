Compare decoding and computing prefix sums using a standard FOR encoding with optimized prefix application to
a PFOR encoding that should have some performance hit due to applying exceptions.

* forDeltaDecoder: The ForDeltaUtil decoding logic currently used in Lucene
* pForDeltaDecoder: A candidate PFOR version of prefix sum decoding being discussed in LUCENE-9850

```
Benchmark                                        (bitsPerValue)  (exceptionCount)   Mode  Cnt   Score    Error   Units
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 0  thrpt    5   9.726 ± 10.518  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 1  thrpt    5  12.363 ±  0.094  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 2  thrpt    5  10.154 ±  9.443  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 3  thrpt    5  12.194 ±  1.162  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 4  thrpt    5  12.219 ±  0.352  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 5  thrpt    5  12.259 ±  0.555  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 6  thrpt    5  10.725 ±  1.160  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                0                 7  thrpt    5  13.062 ±  0.910  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 0  thrpt    5   8.175 ±  5.715  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 1  thrpt    5   8.602 ±  1.580  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 2  thrpt    5   8.885 ±  0.954  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 3  thrpt    5   8.762 ±  1.678  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 4  thrpt    5   7.179 ±  6.350  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 5  thrpt    5   8.890 ±  0.256  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 6  thrpt    5   9.100 ±  0.361  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                1                 7  thrpt    5   8.950 ±  1.351  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 0  thrpt    5   8.395 ±  1.893  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 1  thrpt    5   8.573 ±  0.584  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 2  thrpt    5   8.563 ±  3.836  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 3  thrpt    5   7.091 ±  3.837  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 4  thrpt    5   6.376 ±  5.784  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 5  thrpt    5   8.635 ±  0.702  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 6  thrpt    5   8.540 ±  1.159  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                2                 7  thrpt    5   8.794 ±  0.479  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 0  thrpt    5   7.902 ±  5.527  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 1  thrpt    5   8.462 ±  1.135  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 2  thrpt    5   8.536 ±  0.993  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 3  thrpt    5   8.552 ±  1.138  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 4  thrpt    5   8.563 ±  0.649  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 5  thrpt    5   7.868 ±  2.926  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 6  thrpt    5   8.679 ±  0.322  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                3                 7  thrpt    5   8.824 ±  0.109  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 0  thrpt    5   7.607 ±  3.900  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 1  thrpt    5   7.710 ±  2.788  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 2  thrpt    5   8.718 ±  0.367  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 3  thrpt    5   8.434 ±  0.844  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 4  thrpt    5   7.736 ±  2.211  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 5  thrpt    5   8.024 ±  2.462  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 6  thrpt    5   7.459 ±  1.885  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                4                 7  thrpt    5   7.056 ±  2.666  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 0  thrpt    5   7.827 ±  1.486  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 1  thrpt    5   8.441 ±  0.154  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 2  thrpt    5   8.418 ±  0.402  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 3  thrpt    5   8.411 ±  0.231  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 4  thrpt    5   8.388 ±  0.332  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 5  thrpt    5   8.400 ±  0.186  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 6  thrpt    5   8.065 ±  1.344  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                5                 7  thrpt    5   8.014 ±  1.864  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 0  thrpt    5   7.926 ±  0.900  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 1  thrpt    5   7.956 ±  0.623  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 2  thrpt    5   7.389 ±  1.742  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 3  thrpt    5   8.070 ±  1.093  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 4  thrpt    5   8.075 ±  0.199  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 5  thrpt    5   8.056 ±  0.228  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 6  thrpt    5   7.669 ±  0.810  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                6                 7  thrpt    5   7.439 ±  1.517  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 0  thrpt    5   7.618 ±  0.699  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 1  thrpt    5   7.874 ±  0.153  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 2  thrpt    5   7.876 ±  0.106  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 3  thrpt    5   7.877 ±  0.111  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 4  thrpt    5   7.597 ±  2.270  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 5  thrpt    5   7.724 ±  0.516  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 6  thrpt    5   7.749 ±  0.448  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                7                 7  thrpt    5   7.843 ±  0.473  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 0  thrpt    5   8.448 ±  0.119  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 1  thrpt    5   8.134 ±  0.720  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 2  thrpt    5   7.773 ±  1.202  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 3  thrpt    5   8.141 ±  0.407  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 4  thrpt    5   8.094 ±  0.548  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 5  thrpt    5   8.271 ±  0.623  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 6  thrpt    5   8.259 ±  0.200  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                8                 7  thrpt    5   8.243 ±  0.252  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 0  thrpt    5   7.456 ±  0.186  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 1  thrpt    5   7.452 ±  0.172  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 2  thrpt    5   7.220 ±  1.204  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 3  thrpt    5   7.443 ±  0.206  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 4  thrpt    5   7.024 ±  1.946  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 5  thrpt    5   7.318 ±  0.878  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 6  thrpt    5   7.262 ±  0.304  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder                9                 7  thrpt    5   7.474 ±  0.113  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 0  thrpt    5   7.334 ±  0.270  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 1  thrpt    5   7.453 ±  0.157  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 2  thrpt    5   7.327 ±  0.201  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 3  thrpt    5   7.318 ±  0.107  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 4  thrpt    5   7.335 ±  0.280  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 5  thrpt    5   7.338 ±  0.455  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 6  thrpt    5   7.373 ±  0.324  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               10                 7  thrpt    5   7.367 ±  0.196  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 0  thrpt    5   6.942 ±  0.576  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 1  thrpt    5   7.234 ±  0.196  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 2  thrpt    5   6.928 ±  1.332  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 3  thrpt    5   7.144 ±  0.379  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 4  thrpt    5   6.940 ±  0.849  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 5  thrpt    5   7.216 ±  0.188  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 6  thrpt    5   7.262 ±  0.332  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               11                 7  thrpt    5   7.184 ±  0.142  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 0  thrpt    5   7.131 ±  0.705  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 1  thrpt    5   7.003 ±  0.248  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 2  thrpt    5   6.974 ±  0.220  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 3  thrpt    5   7.107 ±  0.572  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 4  thrpt    5   7.089 ±  0.123  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 5  thrpt    5   6.852 ±  0.212  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 6  thrpt    5   7.028 ±  0.422  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               12                 7  thrpt    5   6.968 ±  0.208  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 0  thrpt    5   6.794 ±  0.204  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 1  thrpt    5   6.871 ±  0.317  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 2  thrpt    5   6.837 ±  0.142  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 3  thrpt    5   6.492 ±  0.284  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 4  thrpt    5   6.807 ±  0.202  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 5  thrpt    5   6.893 ±  0.238  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 6  thrpt    5   6.856 ±  0.531  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               13                 7  thrpt    5   6.610 ±  1.025  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 0  thrpt    5   6.676 ±  0.088  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 1  thrpt    5   6.511 ±  0.357  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 2  thrpt    5   6.595 ±  0.238  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 3  thrpt    5   6.702 ±  0.331  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 4  thrpt    5   6.714 ±  0.164  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 5  thrpt    5   6.619 ±  0.133  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 6  thrpt    5   6.609 ±  0.228  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               14                 7  thrpt    5   6.612 ±  0.322  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 0  thrpt    5   6.690 ±  0.112  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 1  thrpt    5   6.374 ±  0.822  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 2  thrpt    5   6.411 ±  0.507  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 3  thrpt    5   6.601 ±  0.288  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 4  thrpt    5   6.186 ±  1.582  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 5  thrpt    5   5.849 ±  0.899  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 6  thrpt    5   6.705 ±  0.110  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               15                 7  thrpt    5   6.645 ±  0.628  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 0  thrpt    5   7.114 ±  0.670  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 1  thrpt    5   7.283 ±  0.122  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 2  thrpt    5   7.239 ±  0.167  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 3  thrpt    5   7.157 ±  0.367  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 4  thrpt    5   7.178 ±  0.594  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 5  thrpt    5   7.201 ±  0.500  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 6  thrpt    5   6.966 ±  1.384  ops/us
PackedIntsDeltaDecodeBenchmark.forDeltaDecoder               16                 7  thrpt    5   7.164 ±  0.358  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 0  thrpt    5  11.358 ±  0.316  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 1  thrpt    5   9.097 ±  0.189  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 2  thrpt    5   8.973 ±  0.748  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 3  thrpt    5   8.604 ±  0.588  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 4  thrpt    5   8.536 ±  0.107  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 5  thrpt    5   8.288 ±  1.209  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 6  thrpt    5   8.467 ±  0.125  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               0                 7  thrpt    5   8.424 ±  0.113  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 0  thrpt    5   9.000 ±  0.119  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 1  thrpt    5   8.022 ±  0.307  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 2  thrpt    5   8.191 ±  0.337  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 3  thrpt    5   7.983 ±  0.532  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 4  thrpt    5   8.220 ±  0.295  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 5  thrpt    5   7.979 ±  0.591  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 6  thrpt    5   7.935 ±  0.114  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               1                 7  thrpt    5   7.962 ±  0.316  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 0  thrpt    5   8.796 ±  0.768  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 1  thrpt    5   7.988 ±  0.098  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 2  thrpt    5   8.206 ±  0.295  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 3  thrpt    5   8.250 ±  0.159  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 4  thrpt    5   7.929 ±  0.638  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 5  thrpt    5   7.850 ±  1.325  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 6  thrpt    5   8.008 ±  0.198  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               2                 7  thrpt    5   7.999 ±  0.115  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 0  thrpt    5   8.536 ±  0.787  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 1  thrpt    5   7.722 ±  0.314  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 2  thrpt    5   6.562 ±  0.371  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 3  thrpt    5   7.500 ±  0.349  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 4  thrpt    5   7.730 ±  0.181  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 5  thrpt    5   7.751 ±  0.086  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 6  thrpt    5   7.852 ±  0.160  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               3                 7  thrpt    5   7.629 ±  0.417  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 0  thrpt    5   8.767 ±  0.189  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 1  thrpt    5   7.875 ±  0.620  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 2  thrpt    5   7.843 ±  0.335  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 3  thrpt    5   7.872 ±  0.286  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 4  thrpt    5   7.661 ±  0.174  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 5  thrpt    5   7.679 ±  0.303  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 6  thrpt    5   7.548 ±  0.383  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               4                 7  thrpt    5   7.586 ±  0.212  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 0  thrpt    5   8.371 ±  0.375  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 1  thrpt    5   7.370 ±  0.388  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 2  thrpt    5   7.633 ±  0.078  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 3  thrpt    5   7.454 ±  0.124  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 4  thrpt    5   7.407 ±  0.203  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 5  thrpt    5   7.496 ±  0.292  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 6  thrpt    5   7.347 ±  0.371  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               5                 7  thrpt    5   7.184 ±  0.429  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 0  thrpt    5   7.984 ±  0.382  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 1  thrpt    5   7.254 ±  1.363  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 2  thrpt    5   7.182 ±  0.898  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 3  thrpt    5   7.267 ±  0.358  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 4  thrpt    5   7.049 ±  0.460  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 5  thrpt    5   7.004 ±  0.486  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 6  thrpt    5   7.049 ±  0.572  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               6                 7  thrpt    5   7.053 ±  0.247  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 0  thrpt    5   7.792 ±  0.320  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 1  thrpt    5   7.237 ±  0.240  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 2  thrpt    5   7.222 ±  0.174  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 3  thrpt    5   6.868 ±  0.110  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 4  thrpt    5   7.121 ±  0.088  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 5  thrpt    5   7.032 ±  0.528  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 6  thrpt    5   7.094 ±  0.128  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               7                 7  thrpt    5   7.013 ±  0.469  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 0  thrpt    5   8.454 ±  0.121  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 1  thrpt    5   7.351 ±  0.247  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 2  thrpt    5   7.591 ±  0.317  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 3  thrpt    5   7.373 ±  0.242  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 4  thrpt    5   7.100 ±  0.768  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 5  thrpt    5   7.327 ±  0.533  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 6  thrpt    5   7.443 ±  0.099  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               8                 7  thrpt    5   7.293 ±  0.183  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 0  thrpt    5   7.298 ±  0.138  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 1  thrpt    5   6.917 ±  0.257  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 2  thrpt    5   6.769 ±  0.840  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 3  thrpt    5   6.778 ±  0.275  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 4  thrpt    5   6.575 ±  0.152  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 5  thrpt    5   6.706 ±  0.364  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 6  thrpt    5   6.513 ±  0.636  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder               9                 7  thrpt    5   6.665 ±  0.110  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 0  thrpt    5   7.295 ±  0.197  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 1  thrpt    5   6.680 ±  0.130  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 2  thrpt    5   6.358 ±  0.569  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 3  thrpt    5   6.430 ±  0.168  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 4  thrpt    5   6.484 ±  0.284  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 5  thrpt    5   6.443 ±  0.362  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 6  thrpt    5   6.475 ±  0.514  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              10                 7  thrpt    5   6.368 ±  0.287  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 0  thrpt    5   6.997 ±  1.682  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 1  thrpt    5   6.439 ±  0.109  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 2  thrpt    5   6.501 ±  0.223  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 3  thrpt    5   6.464 ±  0.575  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 4  thrpt    5   5.926 ±  0.827  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 5  thrpt    5   6.419 ±  0.323  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 6  thrpt    5   6.452 ±  0.148  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              11                 7  thrpt    5   6.108 ±  0.177  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 0  thrpt    5   6.884 ±  0.350  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 1  thrpt    5   5.597 ±  1.957  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 2  thrpt    5   6.087 ±  0.275  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 3  thrpt    5   6.012 ±  0.586  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 4  thrpt    5   6.023 ±  0.258  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 5  thrpt    5   6.064 ±  0.146  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 6  thrpt    5   5.870 ±  0.437  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              12                 7  thrpt    5   5.594 ±  0.818  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 0  thrpt    5   6.463 ±  0.271  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 1  thrpt    5   6.210 ±  0.228  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 2  thrpt    5   5.960 ±  0.728  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 3  thrpt    5   5.850 ±  0.199  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 4  thrpt    5   5.930 ±  0.280  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 5  thrpt    5   5.727 ±  1.061  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 6  thrpt    5   5.846 ±  0.769  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              13                 7  thrpt    5   5.870 ±  0.229  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 0  thrpt    5   6.426 ±  0.146  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 1  thrpt    5   5.977 ±  0.464  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 2  thrpt    5   5.763 ±  0.125  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 3  thrpt    5   5.856 ±  0.194  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 4  thrpt    5   5.759 ±  0.355  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 5  thrpt    5   5.737 ±  0.529  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 6  thrpt    5   5.744 ±  0.200  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              14                 7  thrpt    5   5.789 ±  0.158  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 0  thrpt    5   6.357 ±  0.267  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 1  thrpt    5   5.700 ±  0.182  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 2  thrpt    5   5.700 ±  0.317  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 3  thrpt    5   5.815 ±  0.096  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 4  thrpt    5   5.715 ±  0.241  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 5  thrpt    5   5.941 ±  0.244  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 6  thrpt    5   5.941 ±  0.148  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              15                 7  thrpt    5   5.687 ±  0.340  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 0  thrpt    5   6.834 ±  0.498  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 1  thrpt    5   6.585 ±  0.199  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 2  thrpt    5   6.462 ±  0.196  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 3  thrpt    5   6.202 ±  0.391  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 4  thrpt    5   6.452 ±  0.160  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 5  thrpt    5   6.515 ±  0.284  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 6  thrpt    5   6.325 ±  0.404  ops/us
PackedIntsDeltaDecodeBenchmark.pForDeltaDecoder              16                 7  thrpt    5   6.184 ±  0.695  ops/us
```
