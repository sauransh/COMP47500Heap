import pandas as pd
import matplotlib.pyplot as plt

results_df = pd.read_csv('benchmark_results.csv')
plt.figure(figsize=(10, 6))

plt.plot(results_df['Size'], results_df['Insertion Time (ns)'], label='Heap Insertion')
plt.plot(results_df['Size'], results_df['Removal Time (ns)'], label='Heap Removal')
plt.plot(results_df['Size'], results_df['Heapify Time (ns)'], label='Heap Heapify')

plt.xlabel('Input Size')
plt.ylabel('Time (ns)')
plt.title('Performance of Heap Data Structure')
plt.legend()

plt.show()
