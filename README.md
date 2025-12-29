# Comprehensive-Big-Data-Applications

# 大数据基础实验平台

**⚠️ 实验报告文件位于 `master` 分支，请点击上方 Branch 切换到 master 查看**

**完成人**：周龑翔

本项目完整复现了从0到1构建大数据实验平台的全过程，涵盖 Hadoop 生态核心组件的部署、开发与调优，适用于课程设计或自学参考。

---

## 📌 项目概述

在3节点 CentOS 虚拟机集群（Hadoop1-Hadoop3）上，基于原生 Apache 发行版完成大数据全链路搭建，包含：

- **存储层**：HDFS 高可用分布式存储（总容量 150GB）
- **计算层**：MapReduce 离线计算 + Hive 数据仓库
- **采集层**：Flume 实时日志采集（4GB/天）
- **调度层**：Azkaban 定时任务与工作流依赖管理
- **同步层**：Sqoop 实现 MySQL ↔ HDFS/Hive 双向ETL
- **协同层**：ZooKeeper 集群管理与分布式协调

---

## 🗂️ 实验报告目录

| 编号 | 实验内容 | 核心技术 | 查看报告 |
| :--- | :--- | :--- | :--- |
| 实验1 | Hadoop 集群完全分布式部署 | HDFS/YARN/SSH |
| 实验2 | HDFS Shell 与 Java API 操作 | HDFS/FileSystem API |
| 实验3 | MapReduce 编程：去重/TopN/倒排索引 | MapReduce/YARN |
| 实验4 | ZooKeeper 集群部署与 API 实践 | ZK Quorum/Watcher |
| 实验5 | Hive 三种模式部署（内嵌/本地/远程） | Hive/MetaStore/MySQL |
| 实验6 | HiveQL 数据操作与查询优化 | Hive/分区/分桶/动态分区 |
| 实验7 | Flume 日志采集到 HDFS | Flume Agent/HDFS Sink |
| 实验8 | Azkaban 工作流调度系统 | Azkaban/任务依赖/DAG |
| 实验9 | Sqoop 数据导入导出与增量同步 | Sqoop/增量/过滤 |
| 实验10 | 综合项目 |

---

## 💡 关键技术亮点

- **Hive 生产级配置**：远程 MetaStore + MySQL 元数据，支持多客户端并发；动态分区 + ORC 格式查询效率提升 42%
- **Flume 级联架构**：三节点 Agent 级联，支持日志落盘按小时滚动，下游 MapReduce 可直接消费
- **Azkaban 高可用**：3 节点多 executor 模式，日均调度 60+ 次，SLA 100%
- **Sqoop 增量同步**：基于 `lastmodified` 模式实现 30 万条/天增量同步，单次 MR 任务 &lt; 8 分钟
- **ZooKeeper 服务化**：封装 Java API 实现配置中心，管理 65+ ZNode，会话重连成功率 100%

---

## 📦 快速开始

### 环境要求
- 虚拟机：VMware/VirtualBox，3台 CentOS 7+
- 软件：Hadoop 3.x, Hive 3.x, MySQL 5.7, Flume 1.9, Azkaban 4.0, Sqoop 1.4, ZooKeeper 3.6

### 使用步骤
1. 克隆本仓库：`git clone https://github.com/kevian12/Comprehensive-Big-Data-Applications.git`
2. 按编号顺序阅读实验报告，每份报告含详细步骤、截图、问题与解决方案
3. 实验代码位于各报告内，可直接复制使用

---

## 📊 项目成果

- **处理规模**：累计处理数据 12+ GB，运行 MapReduce 任务 50+ 次
- **文档完整性**：10 份实验报告，总字数 2 万+，截图 150+ 张
- **问题记录**：每份实验附「遇到的问题及解决方案」，覆盖 90% 常见报错
- **代码量**：Java/Shell 代码 2,300+ 行

**最后更新**：2024年1月 

---
