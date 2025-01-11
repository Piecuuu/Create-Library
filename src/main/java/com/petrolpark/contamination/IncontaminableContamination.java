package com.petrolpark.contamination;

import java.util.stream.Stream;

public final class IncontaminableContamination implements IContamination<Object, Object> {

    public static final IncontaminableContamination INSTANCE = new IncontaminableContamination();

    private static final Object OBJECT = new Object();

    @Override
    public Contaminable<Object, Object> getContaminable() {
        return Contaminables.NOT;
    };

    @Override
    public Object getType() {
        return OBJECT;
    };

    @Override
    public double getAmount() {
        return 0d;
    };

    @Override
    public void save() {};

    @Override
    public boolean has(Contaminant contaminant) {
        return false;
    };

    @Override
    public boolean hasAnyContaminant() {
        return false;
    };

    @Override
    public boolean hasAnyExtrinsicContaminant() {
        return false;
    };

    @Override
    public Stream<Contaminant> streamAllContaminants() {
        return Stream.empty();
    };

    @Override
    public boolean contaminate(Contaminant contaminant) {
        return false;
    };

    @Override
    public boolean contaminateAll(Stream<Contaminant> contaminantsStream) {
        return false;
    };

    @Override
    public boolean decontaminate(Contaminant contaminant) {
        return false;
    };

    @Override
    public boolean decontaminateOnly(Contaminant contaminant) {
        return false;
    };

    @Override
    public boolean fullyDecontaminate() {
        return false;
    };
    
};
