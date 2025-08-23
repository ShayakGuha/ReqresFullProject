package org.reqres.models;

import java.util.List;

public class ReqresResponse {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Data> data;
    public Support support;
}
