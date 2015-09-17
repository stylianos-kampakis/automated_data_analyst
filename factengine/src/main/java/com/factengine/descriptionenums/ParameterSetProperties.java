package com.factengine.descriptionenums;

public enum ParameterSetProperties {
	//this means that the parameter set refers to kernel methods
	KERNEL,
	//the param set refers to neural networks
	NEURAL,
	//good when more features than instances
	REGULARIZER,
	//things such as L1 regularization
	AGGRESSIVE_REGULARIZER,
	SPARSE_REGULARIZER,
	//this is for things such as a polynomial kernel
	POLYNOMIAL,
	//this refers to simple parameter sets, which tend to run fast. E.g. neural network with few neurons or SVM with linear kernel.
	FAST
}
