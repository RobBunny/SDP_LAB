import pymongo
import threading
import time
import os
from enum import Enum
from dotenv import load_dotenv

load_dotenv()
MONGO_URI = os.getenv("MONGO_URI")

class MongoDBConnection:
    """The actual connection object"""
    def __init__(self):
        time.sleep(0.1)
        self._client = pymongo.MongoClient(MONGO_URI)

    def get_database(self, db_name="SingletonDB"):
        return self._client[db_name]

    def get_collection(self, db_name, col_name):
        return self._client[db_name][col_name]

    def close_connection(self):
        self._client.close()

class EnumSingletonMongoDB(Enum):
    INSTANCE = MongoDBConnection()

    def get_database(self, db_name="SingletonDB"):
        return self.value.get_database(db_name)

    def get_collection(self, db_name, col_name):
        return self.value.get_collection(db_name, col_name)

    def close_connection(self):
        self.value.close_connection()


# Usage
#m1 = EnumSingletonMongoDB.INSTANCE
#m2 = EnumSingletonMongoDB.INSTANCE
#print(id(m1) == id(m2))  # True — Enum guarantees this

# ── Experiment 1: Instance Validation ──────────────────────────
def test_instance_validation(SingletonClass, name, use_enum=False):
    print(f"\n{'='*50}")
    print(f"Instance Validation: {name}")
    if use_enum:
        instances = [SingletonClass.INSTANCE for _ in range(5)]
    else:
        instances = [SingletonClass.get_instance() for _ in range(5)]

    ids = [id(i) for i in instances]
    print(f"IDs: {ids}")
    print(f"All same? {len(set(ids)) == 1}")


# ── Experiment 2: Thread Safety ─────────────────────────────────
def test_thread_safety(SingletonClass, name, use_enum=False):
    print(f"\n{'='*50}")
    print(f"Thread Safety: {name}")
    results = []

    def get_inst():
        if use_enum:
            results.append(id(SingletonClass.INSTANCE))
        else:
            results.append(id(SingletonClass.get_instance()))

    threads = [threading.Thread(target=get_inst) for _ in range(10)]
    for t in threads: t.start()
    for t in threads: t.join()

    print(f"Unique IDs collected: {set(results)}")
    print(f"Thread-safe? {len(set(results)) == 1}")


# ── Experiment 3: Performance ────────────────────────────────────
def test_performance(SingletonClass, name, use_enum=False):
    print(f"\n{'='*50}")
    print(f"Performance: {name}")

    # Init time
    start = time.perf_counter()
    if use_enum:
        _ = SingletonClass.INSTANCE
    else:
        _ = SingletonClass.get_instance()
    init_time = time.perf_counter() - start
    print(f"Init time: {init_time:.6f}s")

    # Access time (100000 repeated calls)
    start = time.perf_counter()
    for _ in range(100000):
        if use_enum:
            _ = SingletonClass.INSTANCE
        else:
            _ = SingletonClass.get_instance()
    access_time = time.perf_counter() - start
    print(f"Access time: {access_time:.6f}s")


# Run all experiments
test_instance_validation(EnumSingletonMongoDB, "Enum", use_enum=True)

test_thread_safety(EnumSingletonMongoDB, "Enum", use_enum=True)

test_performance(EnumSingletonMongoDB, "Enum", use_enum=True)