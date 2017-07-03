#import "DevToDevWrappeer.h"
#import <devtodev/devtodev.h>
#import <AdjustSdk/Adjust.h>
#include <asl.h>
#include <stdio.h>


@implementation DevToDevWrappeer

const int EVENT_OTHER_SOCIAL = 70;
extern int CreateDsMap( int _num, ... );
extern void CreateAsynEventWithDSMap(int dsmapindex, int event_index);

- (void)ReturnAsync:(double)arg0 Arg2:(double)arg1
{
    int dsMapIndex = CreateDsMap(3,
        "type", 0.0, "finished",
        "argument0", arg0, (void*)NULL,
        "argument1", arg1, (void*)NULL
	);
	
	CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
}

- (double) AddTwoNumbers:(double)arg0 Arg2:(double)arg1
{
    double value = arg0 + arg1;
    NSLog(@"yoyo: %f + %f = %f", arg0, arg1, value);

    return value;
}

- (double) staticInit
{
	NSLog(@"INIT DevToDev");
	[DevToDev initWithKey:@"459e6b88-3155-01d8-941e-7001c50f3172" andSecretKey:@"NjH4byU02u6AgsvxCJfqIB3cLwVYtZeo"];
	
	
	NSString *yourAppToken = @"dfzdd7h4rlkw";
	NSString *environment = ADJEnvironmentProduction;
	ADJConfig *adjustConfig = [ADJConfig configWithAppToken:yourAppToken
                                   environment:environment];

	[Adjust appDidLaunch:adjustConfig];
	
	return 1.0;
}


- (double) staticFinal
{
	// EMPTY
	return 1.0;
}


- (double) customEvent:(char *)arg0
{
	NSLog(@"CUSTOM EVENT: %s", arg0);
	NSString *eventName = [NSString stringWithFormat:@"%s",arg0];
	[DevToDev customEvent:eventName];
	
	return 1.0;
}


- (double) levelUp:(double)arg0
{
	NSUInteger level = arg0;
	[DevToDev levelUp:level];

	return 1.0;
}

- (double) tutorialCompleted:(double)arg0
{
	NSUInteger step = arg0;
	[DevToDev tutorialCompleted: step];

	return 1.0;
}


- (double) progressionEvent:(char *)locationName
	Arg2:(double) spentTime
	Arg3:(double) spentDot
	Arg4:(double) spentBomb
	Arg5:(double) spentPaint
	Arg6:(double) spentTurns
	Arg7:(double) spentMoreTurns
	Arg8:(double) spentOpenBoxes
	Arg9:(double) success
	Arg10:(double) difficulty
	Arg11:(double) playHour
	Arg12:(double) coinsSpentProgress
	Arg13:(double) coinsSpentFinal
	Arg14:(double) coinsBalance
{
	LocationEventParams* params = [[LocationEventParams alloc] init];
	[params setDifficulty:(int)difficulty];
	//[params setSource: @"Vilage step 02"];
	
	NSString *eventId = [NSString stringWithFormat:@"%s", locationName];
	NSNumber *duration = [NSNumber numberWithInt:(int)spentTime];
	[DevToDev startProgressionEvent:eventId withParameters:params];

	[params setIsSuccess: (success > 0.5)];
	[params setDuration: duration];
	
	NSNumber *nsSpentDot = [NSNumber numberWithInt:(int)spentDot];
	NSNumber *nsSpentBomb = [NSNumber numberWithInt:(int)spentBomb];
	NSNumber *nsSpentPaint = [NSNumber numberWithInt:(int)spentPaint];
	NSNumber *nsSpentTurns = [NSNumber numberWithInt:(int)spentTurns];
	NSNumber *nsSpentMoreTurns = [NSNumber numberWithInt:(int)spentMoreTurns];
	NSNumber *nsSpentOpenBoxes = [NSNumber numberWithInt:(int)spentOpenBoxes];
	NSNumber *nsPlayHour = [NSNumber numberWithInt:(int)playHour];
	
	NSNumber *nsCoinsSpentProgress = [NSNumber numberWithInt:(int)coinsSpentProgress];
	NSNumber *nsCoinsBalance = [NSNumber numberWithInt:(int)coinsBalance];
	
	NSDictionary* spent = @{
		@"Dot" : nsSpentDot,
		@"Bomb" : nsSpentBomb,
		@"Paint" : nsSpentPaint,
		@"Turns" : nsSpentTurns,
		@"MoreTurns" : nsSpentMoreTurns,
		@"OpenBoxes" : nsSpentOpenBoxes,
		@"playHour" : nsPlayHour,
		@"Coins trial" : nsCoinsSpentProgress,
		@"Coins balance" : nsCoinsBalance,
	};
	
	NSMutableDictionary *mutableSpent = [spent mutableCopy];
	
	if (success > 0.5)
	{
		NSNumber *nsCoinsSpentFinal = [NSNumber numberWithInt:(int)coinsSpentFinal];
		mutableSpent[@"Coins spent final"] = nsCoinsSpentFinal;
	}
		
	[params setSpent: mutableSpent];
	
	/*
	NSNumber *nsEarnedCoins = [NSNumber numberWithInt:(int)earnedCoins];
	
	NSDictionary* earned = @{
		@"Coins" : nsEarnedCoins
	};
	[params setEarned: earned];
	*/

	[DevToDev endProgressionEvent:eventId withParameters:params];
		
	return 1.0;
}


- (double) inAppPurchase:(char*)purchaseId
		Arg2:(char*) purchaseType
		Arg3:(double) purchaseAmount
		Arg4:(double) purchasePrice
		Arg5:(char*) purchaseCurrency
{
	NSString *nsPurchaseId = [NSString stringWithFormat:@"%s",purchaseId];
	NSString *nsPurchaseType = [NSString stringWithFormat:@"%s",purchaseType];
	NSInteger nsPurchaseAmount = purchaseAmount;
	NSInteger nsPurchasePrice = purchasePrice;
	NSString *nsPurchaseCurrency = [NSString stringWithFormat:@"%s",purchaseCurrency];
	
	[DevToDev inAppPurchase:nsPurchaseId
		withPurchaseType:nsPurchaseType
		andPurchaseAmount:nsPurchaseAmount
		andPurchasePrice:nsPurchasePrice
		andPurchaseCurrency:nsPurchaseCurrency];
	
	return 1.0;
}


- (double) realPayment:(char*)paymentId
		Arg2:(double) inAppPrice
		Arg3:(char*) inAppName
		Arg4:(char*) inAppCurrencyISOCode
{
	NSString *nsPaymentId = [NSString stringWithFormat:@"%s",paymentId];
	NSString *nsInAppName = [NSString stringWithFormat:@"%s",inAppName];
	NSString *nsInAppCurrencyISOCode = [NSString stringWithFormat:@"%s",inAppCurrencyISOCode];
	
	[DevToDev realPayment:nsPaymentId
		withInAppPrice:inAppPrice
		andInAppName:nsInAppName
		andInAppCurrencyISOCode:nsInAppCurrencyISOCode];
	
	return 1.0;
}


- (double) setUserInfo:(char*)gender
		Arg2:(char*) name
		Arg3:(double) ageRange
		Arg4:(char*) userID
		Arg5:(char*) locale
		Arg6:(char*) email
		Arg7:(double) moneySpent
{
	NSString *nsGender = [NSString stringWithFormat:@"%s", name];
	if ([nsGender isEqualToString:@"male"])
	{
		DevToDev.activeUser.gender = Male;
	}
	else
	{
		DevToDev.activeUser.gender = Female;
	}
	
	DevToDev.activeUser.email = [NSString stringWithFormat:@"%s",email];
	
	NSString *nsName = [NSString stringWithFormat:@"%s", name];
	NSNumber *nsAgeRange = [NSNumber numberWithInt:(int)ageRange];
	NSString *nsId = [NSString stringWithFormat:@"%s", userID];
	NSString *nsLocale = [NSString stringWithFormat:@"%s", locale];
	NSNumber *nsMoneySpent = [NSNumber numberWithInt:(int)moneySpent];
	
	NSDictionary* userData = @{
		@"name" : nsName,
		@"ageRange" : nsAgeRange,
		@"fb-id" : nsId,
		@"locale" : nsLocale,
		@"moneySpent" : nsMoneySpent,
	};

	[DevToDev.activeUser setUserData: userData];

	return 1.0;
}


- (NSString *) BuildAString:(char *)arg0 Arg2:(char *)arg1
{
    NSString *value = [NSString stringWithFormat:@"%s%s",arg0,arg1];//[arg0 stringByAppendingString:arg1];
    NSLog(@"yoyo: %s + " " + %s = %@", arg0, arg1, value);

    return value;
}

- (NSString *) HowManyObjects:(double)arg0 Arg2:(double)arg1 Arg3:(char *)arg2
{
    double value = arg0 + arg1;
    NSLog(@"yoyo: %f + %f = %f", arg0, arg1, value); 
    NSString *arg2ns = [NSString stringWithFormat:@"%s",arg2];
    NSString *myString = [NSString stringWithFormat:@"%f %@", value,arg2ns];
    NSLog(@"yoyo: %@", myString);

    return myString;

}

// for android compatability
- (NSString *) getSignatureWithToken:(char *)arg0
{
	NSString *myString = [NSString stringWithFormat:@"iOS always empty"];
	return myString;
}

@end