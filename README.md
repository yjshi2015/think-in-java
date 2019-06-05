# think-in-java
Think-In-Java 重温经典

## Queue
- Queue可以从两个维度来分类。一个维度是**阻塞与非阻塞**,所谓阻塞是指队列已满时入队操作阻塞,队列为空时出队操作阻塞.
另一个维度是**单端与双端**,单端指的是只能队尾入队,队首出队;而双端指的是队首队尾
皆可入队出队.Java并发包里**阻塞队列都用Blocking关键字标示,单队列使用Queue标识,双队列使用DeQue标示**

- 实际工作中,一般不建议使用无界队列(即队列的大小无限制),因为数据量大了很容易造成OOM,只有ArrayBlockingQueue和LinkedBlockingQueue是支持有界的.

## concurrent
- 该包下的工具类是线程安全的,包括list/map/Queue/autoInteger等等
- 对于简单的并行任务,可以通过"线程池+Future"的方案来解决
- 对于任务之间有聚合关系,不管是AND聚合,还是OR聚合,都可以通过"CompletableFuture"来解决
- 对于批量的并行任务,则可以通过"CompletionService"来解决


## 度量性能的指标
- 延迟:指发出请求到接收到响应这个过程的时间,常用的指标有TP99 / TP999
- 吞吐量:指单位时间内能处理请求的数量,常用指标 QPS/TPS

## 最佳线程数
- CPU密集型: 最佳线程数 = CPU核数 + 1
  理论上线程数量=CPU核数,但在工程中,当线程因为偶尔的内存页失效或其他原因导致阻塞时,这个额外的线程可以顶上,从而保证
  CPU的利用率达到100%
  
- IO密集型:  最佳线程数 = CPU核数 * (1 + IO耗时/CPU耗时)
  令R=IO耗时/CPU耗时,则意味着当线程A执行完IO操作后,另外R个线程正好执行完各自的CPU计算.这样CPU的利用率就达到了100%,
  同时IO设备的使用率也达到了100%
  
- 针对CPU密集型/IO密集场景下"最佳线程数"的计算原理,开抽象为:让所有的任务由并驾齐驱改为错峰执行,以便提高硬件设备的利
  用率.