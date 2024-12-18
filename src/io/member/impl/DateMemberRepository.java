package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DateMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-data.dat";

    @Override
    public void add(Member member) {
        try(FileOutputStream fs = new FileOutputStream(FILE_PATH, true);
            DataOutputStream ds = new DataOutputStream(fs)){

            ds.writeUTF(member.getId());
            ds.writeUTF(member.getName());
            ds.writeInt(member.getAge());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();

        try(FileInputStream fs = new FileInputStream(FILE_PATH);
            DataInputStream ds = new DataInputStream(fs)){

            while(0 < ds.available()){
                Member member = new Member(ds.readUTF(), ds.readUTF(), ds.readInt());
                members.add(member);
            }
        } catch (FileNotFoundException e){
            return new ArrayList<>();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        return members;
    }
}
