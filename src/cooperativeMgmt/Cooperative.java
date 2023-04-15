package cooperativeMgmt;

import java.util.*;

public class Cooperative {

    private List<String> memberList = new ArrayList<>();
    private Map<String, Integer> enrollments = new HashMap<>();
    private Map<String, Member> memberMap = new TreeMap<>();
    private Map<String, Product> productMap = new TreeMap<>();
    private Map<String, Campaign> campaignMap = new TreeMap<>();
    private Map<String, List<Integer>> payments = new HashMap<>();
    private Map<String, Map<String, Integer>> orders = new HashMap<>();


    //R1
    public List<String> addMembers(String... names) throws CMException {
        Set<String> membersName = new TreeSet<>();
        for (String name : names) {
            if (!memberList.contains(name) && !membersName.contains(name)) {
                memberList.add(name);
                membersName.add(name);
            }
        }

        return new ArrayList<>(membersName);
    }


    public List<String> addProducts(String... items) {
        List<String> productsName = new ArrayList<>();
        for (String productId : items) {
            String[] products = productId.split(":");
            String productIds = products[0];
            int p = Integer.parseInt(products[1]);
            enrollments.put(productIds, p);
            productsName.add(productIds);
        }
        productsName.sort(String::compareTo);
        return productsName;
    }

    public List<String> addCampaign(String id, String... productIds) throws CMException {
        Set<String> campaign = new TreeSet<>();
        Campaign campaign1 = new Campaign(id);
        campaignMap.put(id, campaign1);
        for (String productId : productIds) {
            Product product = new Product(productId);
            if (!productMap.containsKey(productId)) {
                campaign.add(productId);
            } else {
                productMap.put(String.valueOf(campaign), product);
            }
        }
        return new ArrayList<>(campaign);

    }


    //R2
    public int join(String memberName, String campaignId) throws CMException {
        if (!memberList.contains(memberName) && campaignMap.containsKey(campaignId)) {
            throw new CMException(" Member not found  " + memberName);
        }
        if (enrollments.containsKey(campaignId)) {
            throw new CMException("CampaignId not found " + campaignId);
        }
        Set<String> memberEnrollments = new HashSet<>();
        if (!memberEnrollments.add(campaignId + 1)) {
        }
        return memberEnrollments.size();
    }


    public int addPayment(String memberName, int amount) throws CMException {
        if (memberMap.containsKey(memberName)) throw new CMException("MemberName not found  ");

        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
        paymentList.add(amount);
        payments.put(memberName, paymentList);
        int totalAmount = 0;
        totalAmount = paymentList.stream().mapToInt(Integer::intValue).sum();
        return totalAmount;
    }

    public List<Integer> getPayments(String memberName) throws CMException {
        if (!memberList.contains(memberName)) throw new CMException("Member not  found ");
        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
        return paymentList;

    }

    //R3
    public int getBalance(String memberName) {
            if (!memberList.contains(memberName)) throw new ArithmeticException("Member not found");

        Map<String, Integer> memberOrders = orders.getOrDefault(memberName, new HashMap<>());
        int balance = 0;
        for (Map.Entry<String, Integer> entry : memberOrders.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            int price = enrollments.get(productId);
            balance -= quantity * price;
        }
        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
        int totalAmount = paymentList.stream().mapToInt(Integer::intValue).sum();
        balance += totalAmount;

        return balance;
//        Map<String, Integer> memberOrders = orders.getOrDefault(memberName, new HashMap<>());
//        int balance = 0;
//        for (Map.Entry<String, Integer> entry : memberOrders.entrySet()) {
//            String productId = entry.getKey();
//            int quantity = entry.getValue();
//            int price = enrollments.get(productId);
//            balance -= quantity * price;
//        }
//        List<Integer> paymentList = payments.getOrDefault(memberName, new ArrayList<>());
//        int totalAmount = paymentList.stream().mapToInt(Integer::intValue).sum();
//        balance += totalAmount;
//        return balance;

//    }
    }


    public int addOrder(String orderId, String memberName, String campaignId, String... items) throws CMException {
        Campaign campaign = new Campaign(orderId);
        if (memberMap.containsKey(orderId)) {
            campaignMap.put(orderId, campaign);
        }
        for (String item : items) {

        }

        return 0;
    }

    //R4
    public SortedMap<String, Integer> nOfUnitsPerProductFromOrders() {

        return new TreeMap<String, Integer>();

    }

    public SortedMap<String, Integer> amountPerMember() {
        return new TreeMap<String, Integer>();
    }
}
