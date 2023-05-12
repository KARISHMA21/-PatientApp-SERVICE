package com.patient_service.bean.model;

import java.util.List;
import java.util.Objects;

public class ListPendingRequest {
    List<PendingRequest> pendingRequests;

    @Override
    public String toString() {
        return "ListPendingRequest{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPendingRequest that = (ListPendingRequest) o;
        return Objects.equals(pendingRequests, that.pendingRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pendingRequests);
    }

    public List<PendingRequest> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(List<PendingRequest> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
}
