# think-in-java
Think-In-Java 重温经典

## Queue
- Queue可以从两个维度来分类。一个维度是**阻塞与非阻塞**,所谓阻塞是指队列已满时入队操作阻塞,队列为空时出队操作阻塞.
另一个维度是**单端与双端**,单端指的是只能队尾入队,队首出队;而双端指的是队首队尾
皆可入队出队.Java并发包里**阻塞队列都用Blocking关键字标示,单队列使用Queue标识,双队列使用DeQue标示**

- 实际工作中,一般不建议使用无界队列(即队列的大小无限制),因为数据量大了很容易造成OOM,只有ArrayBlockingQueue和LinkedBlockingQueue是支持有界的.

## concurrent
该包下的工具类是线程安全的,包括list/map/Queue/autoInteger等等