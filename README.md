# Java8-AdderAndAccumulator
1. Use of LongAdder / Accumulator  
2. LongAdder vs AtomicLong

## LongAdder vs AtomicLong 
When you have large number of threads and each thread is updating the same value multiple(may be 10000) times LongAdder is preferred over AtomicLong
