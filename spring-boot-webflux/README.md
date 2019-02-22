### SpringBoot-WebFlux
在学习WebFlux之前，首先得了解Reactive Streams Backpressure

- Reactive Streams 响应式流
  - 发布者，发布元素到订阅者
  - 订阅者，消费元素
  - 订阅：在发布者中，订阅被创建时，将与订阅者共享
  - 处理器：发布者与订阅者之间处理数据。
- Back pressure 背压
  - 背压是一种常用策略，使得发布者拥有无限制的缓冲区存储元素，用于确保发布者发布元素太快，不会去压制订阅者。
- SpringBoot-WebFlux 特性
  - 响应式API
  - 编程模型
  - 适用性
  - 内嵌容器
  - Starter组件
- 编程模型
  - 注解控制层，和MVC保持一致，WebFlux也支持响应式@ResponseBody注解
  - 功能性断点，基于Lambda轻量级编程模型，用来路由和处理请求的工具，与上面不同之处在于，此模型，全程控制了请求-响应的生命流程。
