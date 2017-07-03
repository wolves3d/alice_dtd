@interface AdjustExt : NSObject
{
}

- (double) staticInit;
- (double) staticFinal;

- (double) adjustEvent:(char *)arg0;
- (double) adjustIAP:(char*)arg0
		Arg2:(double) purchasePrice
		Arg3:(char*) purchaseCurrency;

@end