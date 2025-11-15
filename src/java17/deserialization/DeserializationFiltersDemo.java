package java17.deserialization;

import java.io.*;

/**
 * Java 17 Context-Specific Deserialization Filters
 * Demonstrates enhanced security for object deserialization
 */
public class DeserializationFiltersDemo {
    public static void main(String[] args) {
        System.out.println("=== Java 17 Context-Specific Deserialization Filters ===\n");
        
        // 1. Basic filter usage
        System.out.println("1. Basic Filter Usage");
        System.out.println("---------------------");
        demonstrateBasicFilter();
        
        // 2. Global filter
        System.out.println("\n2. Global Filter");
        System.out.println("---------------");
        demonstrateGlobalFilter();
        
        // 3. Custom filter
        System.out.println("\n3. Custom Filter");
        System.out.println("---------------");
        demonstrateCustomFilter();
        
        // 4. Filter patterns
        System.out.println("\n4. Filter Patterns");
        System.out.println("-----------------");
        demonstrateFilterPatterns();
    }
    
    private static void demonstrateBasicFilter() {
        // Create a filter that allows only specific classes
        ObjectInputFilter filter = ObjectInputFilter.Config.createFilter(
            "java.base.*;!*");
        
        System.out.println("Filter pattern: java.base.*;!*");
        System.out.println("  - Allows: java.base.* classes");
        System.out.println("  - Rejects: All other classes");
        
        // Test the filter
        testFilter(filter, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        
        // Note: In real usage, you would set this on ObjectInputStream
        // ObjectInputStream ois = ...;
        // ois.setObjectInputFilter(filter);
    }
    
    private static void demonstrateGlobalFilter() {
        // Set a global filter for all ObjectInputStream instances
        ObjectInputFilter.Config.setSerialFilter(
            ObjectInputFilter.Config.createFilter("java.base.*;!*"));
        
        System.out.println("Global filter set:");
        System.out.println("  - Applies to all ObjectInputStream instances");
        System.out.println("  - Can be overridden by per-stream filters");
    }
    
    private static void demonstrateCustomFilter() {
        // Custom filter that checks class name
        ObjectInputFilter customFilter = info -> {
            String className = info.serialClass() != null 
                ? info.serialClass().getName() 
                : null;
            
            if (className == null) {
                return ObjectInputFilter.Status.UNDECIDED;
            }
            
            // Allow only classes from specific packages
            if (className.startsWith("java.") || 
                className.startsWith("com.misc.java17.deserialization.")) {
                return ObjectInputFilter.Status.ALLOWED;
            }
            
            // Reject everything else
            return ObjectInputFilter.Status.REJECTED;
        };
        
        System.out.println("Custom filter:");
        System.out.println("  - Allows: java.* and com.misc.java17.deserialization.*");
        System.out.println("  - Rejects: All other classes");
        
        // Test the custom filter
        testFilter(customFilter, "java.lang.String", "com.misc.java17.deserialization.SerializableData", "com.example.Custom");
    }
    
    private static void demonstrateFilterPatterns() {
        System.out.println("Common filter patterns with code examples:");
        System.out.println();
        
        // 1. Allow all
        System.out.println("1. Allow all:");
        ObjectInputFilter allowAll = ObjectInputFilter.Config.createFilter("*");
        testFilter(allowAll, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        System.out.println();
        
        // 2. Reject all
        System.out.println("2. Reject all:");
        ObjectInputFilter rejectAll = ObjectInputFilter.Config.createFilter("!*");
        testFilter(rejectAll, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        System.out.println();
        
        // 3. Allow specific package
        System.out.println("3. Allow specific package:");
        ObjectInputFilter allowJavaBase = ObjectInputFilter.Config.createFilter("java.base.*");
        testFilter(allowJavaBase, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        System.out.println();
        
        // 4. Allow package, reject others
        System.out.println("4. Allow package, reject others:");
        ObjectInputFilter allowJavaBaseOnly = ObjectInputFilter.Config.createFilter("java.base.*;!*");
        testFilter(allowJavaBaseOnly, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        System.out.println();
        
        // 5. Multiple packages
        System.out.println("5. Multiple packages:");
        ObjectInputFilter allowMultiple = ObjectInputFilter.Config.createFilter("java.base.*;java.util.*;!*");
        testFilter(allowMultiple, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        System.out.println();
        
        // 6. Array size limit
        System.out.println("6. Array size limit:");
        ObjectInputFilter arrayLimit = ObjectInputFilter.Config.createFilter("maxarray=1000");
        testFilterWithArray(arrayLimit, 500, 1500);
        System.out.println();
        
        // 7. Depth limit
        System.out.println("7. Depth limit:");
        ObjectInputFilter depthLimit = ObjectInputFilter.Config.createFilter("maxdepth=10");
        testFilterWithDepth(depthLimit, 5, 15);
        System.out.println();
        
        // 8. Combined
        System.out.println("8. Combined pattern:");
        ObjectInputFilter combined = ObjectInputFilter.Config.createFilter("java.base.*;maxarray=1000;maxdepth=10;!*");
        testFilter(combined, "java.lang.String", "java.util.ArrayList", "com.example.Custom");
        testFilterWithArray(combined, 500, 1500);
        System.out.println();
    }
    
    private static void testFilter(ObjectInputFilter filter, String... classNames) {
        for (String className : classNames) {
            ObjectInputFilter.FilterInfo info = createFilterInfo(className);
            ObjectInputFilter.Status status = filter.checkInput(info);
            System.out.println("  " + className + " -> " + status);
        }
    }
    
    private static void testFilterWithArray(ObjectInputFilter filter, int smallSize, int largeSize) {
        ObjectInputFilter.FilterInfo smallArray = createArrayFilterInfo(smallSize);
        ObjectInputFilter.FilterInfo largeArray = createArrayFilterInfo(largeSize);
        
        System.out.println("  Array size " + smallSize + " -> " + filter.checkInput(smallArray));
        System.out.println("  Array size " + largeSize + " -> " + filter.checkInput(largeArray));
    }
    
    private static void testFilterWithDepth(ObjectInputFilter filter, int smallDepth, int largeDepth) {
        ObjectInputFilter.FilterInfo smallDepthInfo = createDepthFilterInfo(smallDepth);
        ObjectInputFilter.FilterInfo largeDepthInfo = createDepthFilterInfo(largeDepth);
        
        System.out.println("  Depth " + smallDepth + " -> " + filter.checkInput(smallDepthInfo));
        System.out.println("  Depth " + largeDepth + " -> " + filter.checkInput(largeDepthInfo));
    }
    
    private static ObjectInputFilter.FilterInfo createFilterInfo(String className) {
        return new ObjectInputFilter.FilterInfo() {
            @Override
            public Class<?> serialClass() {
                try {
                    return Class.forName(className);
                } catch (ClassNotFoundException e) {
                    // Return a mock class for demonstration
                    return Object.class;
                }
            }
            
            @Override
            public long arrayLength() {
                return -1;
            }
            
            @Override
            public long depth() {
                return 1;
            }
            
            @Override
            public long references() {
                return 1;
            }
            
            @Override
            public long streamBytes() {
                return 0;
            }
        };
    }
    
    private static ObjectInputFilter.FilterInfo createArrayFilterInfo(long arrayLength) {
        return new ObjectInputFilter.FilterInfo() {
            @Override
            public Class<?> serialClass() {
                return String[].class;
            }
            
            @Override
            public long arrayLength() {
                return arrayLength;
            }
            
            @Override
            public long depth() {
                return 1;
            }
            
            @Override
            public long references() {
                return 1;
            }
            
            @Override
            public long streamBytes() {
                return 0;
            }
        };
    }
    
    private static ObjectInputFilter.FilterInfo createDepthFilterInfo(long depth) {
        return new ObjectInputFilter.FilterInfo() {
            @Override
            public Class<?> serialClass() {
                return String.class;
            }
            
            @Override
            public long arrayLength() {
                return -1;
            }
            
            @Override
            public long depth() {
                return depth;
            }
            
            @Override
            public long references() {
                return 1;
            }
            
            @Override
            public long streamBytes() {
                return 0;
            }
        };
    }
    
    // Example class for serialization
    static class SerializableData implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int value;
        
        public SerializableData(String name, int value) {
            this.name = name;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return "SerializableData{name='" + name + "', value=" + value + "}";
        }
    }
}

