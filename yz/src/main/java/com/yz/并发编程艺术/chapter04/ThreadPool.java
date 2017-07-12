package com.yz.并发编程艺术.chapter04;

/**
 * 6-19
 */
public interface ThreadPool<Job extends Runnable> {
    // ִ��һ��Job�����Job��Ҫʵ��Runnable
    void execute(Job job);

    // �ر��̳߳�
    void shutdown();

    // ���ӹ������߳�
    void addWorkers(int num);

    // ���ٹ������߳�
    void removeWorker(int num);

    // �õ����ڵȴ�ִ�е���������
    int getJobSize();
}
