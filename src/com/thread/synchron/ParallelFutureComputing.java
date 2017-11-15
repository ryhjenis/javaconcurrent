/**
 * CALLABLE实现多线程(有返回值)
 * 
 * 题目
 * 求1到1000000000的和
 * 条件有四核CPU分别是四G内存的
 * 
 * 解决方案采用多线程拆分任务实现(Future)
 * 
 */
package com.thread.synchron;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelFutureComputing {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 单线程方式测试
		long start = System.currentTimeMillis();
		getSumTotal(1, 1000000000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		// 线程池方式测试
		final int cpuCoreNum = 4;
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

		MyThreadTask t1 = new MyThreadTask(1, 250000000);
		MyThreadTask t2 = new MyThreadTask(250000001, 500000000);
		MyThreadTask t3 = new MyThreadTask(500000001, 750000000);
		MyThreadTask t4 = new MyThreadTask(750000001, 1000000000);

		// 调用service.submit启动线程
		Future<Integer> f1 = service.submit(t1);
		Future<Integer> f2 = service.submit(t2);
		Future<Integer> f3 = service.submit(t3);
		Future<Integer> f4 = service.submit(t4);

		start = System.currentTimeMillis(); // 调用FUTURE.GET的阻塞方法,记录最终完成的时间
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println(end - start);

	}

	/**
	 * CALLABLE实现多线程
	 *
	 */
	static class MyThreadTask implements Callable<Integer> {
		int beginNum;
		int endNum;

		MyThreadTask(int beginNum, int endNum) {
			this.beginNum = beginNum;
			this.endNum = endNum;
		}

		@Override
		public Integer call() throws Exception {
			return getSumTotal(beginNum, endNum);
		}

	}

	/**
	 * 实现beginNum 到 endNum求乘级
	 * 
	 * @param beginNum
	 * @param endNum
	 * @return
	 */
	static int getSumTotal(int beginNum, int endNum) {
		int sumTotal = 1;
		for (int i = beginNum; i <= endNum; i++) {
			sumTotal = sumTotal * i;
		}
		return sumTotal;
	}
}
