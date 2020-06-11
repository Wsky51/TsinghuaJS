package com.leetcode;

import java.util.*;

/**
 * Created on 2020/4/13
 *
 * @author WuYi
 */
class Twitter {
    private Map <Integer, User> map;

    public static void main(String[] args) {
//        Twitter twitter = new Twitter();
//        twitter.postTweet(1, 5);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.follow(1, 2);
//        twitter.postTweet(2, 6);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.unfollow(1, 2);
//        System.out.println(twitter.getNewsFeed(1));
        printLong();
    }

    static void printLong() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(2, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 94);
        twitter.postTweet(2, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(2, 22);
        twitter.postTweet(1, 11);
        twitter.postTweet(1, 205);
        twitter.postTweet(2, 203);
        twitter.postTweet(1, 201);
        twitter.postTweet(2, 213);
        twitter.postTweet(1, 200);
        twitter.postTweet(2, 202);
        twitter.postTweet(1, 204);
        twitter.postTweet(2, 208);
        twitter.postTweet(2, 233);
        twitter.postTweet(1, 222);
        twitter.postTweet(2, 211);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        map = new HashMap <Integer, User>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        User user = map.get(userId);
        if (user == null) {
            user = new User(userId);
            map.put(userId, user);
        }
        user.addTwi(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List <Integer> getNewsFeed(int userId) {
        User user = map.get(userId);
        if (user == null) {
            user = new User(userId);
            map.put(userId, user);
            return Collections.EMPTY_LIST;//该用户都不存在，直接返回空列表
        }
        List <Integer> list = new ArrayList <>();
        List <Twi> showList = user.showList;
        int count = 0;
        for (Twi t : showList) {
            list.add(t.tid);
            count++;
            if (count == 10) {
                return list;
            }
        }
        return list;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        User follower = map.get(followerId);
        if (follower == null) {
            follower = new User(followerId);
            map.put(followerId, follower);
        }
        User followee = map.get(followeeId);
        if (followee == null) {
            followee = new User(followeeId);
            map.put(followeeId, followee);
        }
        follower.addFollower(followee);//添加一个关注
        followee.addFollowee(follower);//添加一个粉丝
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }
        User follower = map.get(followerId);
        if (follower == null) {
            follower = new User(followerId);
            map.put(followerId, follower);
            return;
        }
        User followee = map.get(followeeId);
        if (followee == null) {
            followee = new User(followerId);
            map.put(followeeId, followee);
            return;
        }
        follower.removeFollower(followee);//移除一个关注
        followee.removeFollowee(follower);//移除一个粉丝
    }

    class User {
        int uid;//用户id
        List <Twi> twitter;//该用户发了哪些推文
        List <User> follow;//该用户关注了哪些人
        List <User> followed;//该用户的粉丝
        List <Twi> showList;//要展示的10条推文

        public User(int id) {
            this.uid = id;
            twitter = new LinkedList <>();
            follow = new LinkedList <>();
            followed = new LinkedList <>();
            showList = new LinkedList <>();
        }

        //新建一条推文
        void addTwi(int tid) {
            Twi twi = new Twi(tid, this);
            twitter.add(0, twi);//将其建立至最前端
            showList.add(0, twi);
            for (int i = 0; i < followed.size(); i++) {//
                followed.get(i).showList.add(0, twi);
            }
        }

        //添加一个关注
        public void addFollower(User u) {
            for (int i = 0; i < follow.size(); i++) {
                if (follow.get(i).equals(u)) {//已经关注了该用户，没必要重新关注
                    return;
                }
            }
            follow.add(0, u);//插入到对头
            int i, j;
            for (i = 0, j = 0; i < u.twitter.size() && j < showList.size(); ) {
                if (showList.get(j).time < u.twitter.get(i).time) {
                    showList.add(j, u.twitter.get(i));
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            while (i < u.twitter.size()) {
                showList.add(u.twitter.get(i));
                i++;
            }
        }

        //添加一个粉丝
        public void addFollowee(User u) {
            for (int i = 0; i < followed.size(); i++) {
                if (followed.get(i).equals(u)) {
                    return;
                }
            }
            followed.add(0, u);//队头
        }

        //移除关注
        public void removeFollower(User user) {
            //移除所有和该用户相关的twi
            for (int i = 0; i < showList.size(); i++) {
                while (i < showList.size() && showList.get(i).u.uid == user.uid) {
//                    System.out.println("当前移除tid:"+showList.get(i).tid+",归属："+showList.get(i).u.uid);
                    Twi remove = showList.remove(i);
                }
            }
            //移除该用户
            for (int i = 0; i < follow.size(); i++) {
                if (follow.get(i).equals(user)) {
                    follow.remove(i);
                    return;
                }
            }
        }

        //移除一个粉丝
        public void removeFollowee(User user) {
            for (int i = 0; i < followed.size(); i++) {
                if (followed.get(i).equals(user)) {
                    followed.remove(i);
                    return;
                }
            }
        }
    }

    //推文
    static class Twi {
        int tid;//推文id
        static long cnt = 0;//发送时间
        long time;
        User u;//由谁发的;

        public Twi(int tid, User u) {
            this.tid = tid;
            this.u = u;
            time = cnt++;
        }

        @Override
        public String toString() {
            return "Twi{" +
                    "tid=" + tid +
                    ", time=" + time +
                    ", u=" + u.uid +
                    '}';
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

