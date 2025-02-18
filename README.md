# EKS AUTO Mode

 EKS Auto Mode abstracts infrastructure details like node configuration and subnet management, while automatically optimizing costs and performance.

This is ideal for developers, new-comers… who want a no-fuss Kubernetes experience. By automating most of the cluster management, Auto Mode helps you focus on deploying and scaling applications seamlessly.

## EKS Auto Mode provides the following high-level features:

Streamline Kubernetes Cluster Management: EKS Auto Mode streamlines EKS management by providing production-ready clusters with minimal operational overhead. With EKS Auto Mode, you can run demanding, dynamic workloads confidently, without requiring deep EKS expertise.

Application Availability: EKS Auto Mode dynamically adds or removes nodes in your EKS cluster based on the demands of your Kubernetes applications. This minimizes the need for manual capacity planning and ensures application availability.

Efficiency: EKS Auto Mode is designed to optimize compute costs while adhering to the flexibility defined by your NodePool and workload requirements. It also terminates unused instances and consolidates workloads onto other nodes to improve cost efficiency.

Security: EKS Auto Mode uses AMIs that are treated as immutable for your nodes. These AMIs enforce locked-down software, enable SELinux mandatory access controls, and provide read-only root file systems. Additionally, nodes launched by EKS Auto Mode have a maximum lifetime of 21 days (which you can reduce), after which they are automatically replaced with new nodes. This approach enhances your security posture by regularly cycling nodes, aligning with best practices already adopted by many customers.

Automated Upgrades: EKS Auto Mode keeps your Kubernetes cluster, nodes, and related components up to date with the latest patches, while respecting your configured Pod Disruption Budgets (PDBs) and NodePool Disruption Budgets (NDBs). Up to the 21-day maximum lifetime, intervention might be required if blocking PDBs or other configurations prevent updates.

Managed Components: EKS Auto Mode includes Kubernetes and AWS cloud features as core components that would otherwise have to be managed as add-ons. This includes built-in support for Pod IP address assignments, Pod network policies, local DNS services, GPU plug-ins, health checkers, and EBS CSI storage.

Customizable NodePools and NodeClasses: If your workload requires changes to storage, compute, or networking configurations, you can create custom NodePools and NodeClasses using EKS Auto Mode. While default NodePools and NodeClasses can’t be edited, you can add new custom NodePools or NodeClasses alongside the default configurations to meet your specific requirements.

Here is the detailed documentation on EKS Automode :

https://docs.aws.amazon.com/eks/latest/userguide/automode.html


## Project Details :

In this project, we will setup EKS Cluster in Auto mode. We wil create Custom VPC and setup cluster in Private subnet of custom VPC. While We will also setup NAT gatewway in Public subnet for internet connectivity.
We will deploy two Spring boot based microservices and setup ingress controller and ingress class. We will setup AWS ALB as Ingress resource. Then, we will see how to configure path based routing to route traffic between two microservices.

## Here is the complete architecture :

![diagram-export-2-17-2025-9_49_37-PM](https://github.com/user-attachments/assets/e65e8796-f53f-4f38-a009-5a27f4421aad)

## Step 1 : Lets build the microservices :

For 1st Microservice we will use the Employee Management System microservice which we have used in the below project :

https://github.com/ahuja012002/AWS-Springboot-EKS

This repository has Microservices details along with Docker file to build image and storing the image in Amazon ECR

For 2nd Microservice we will now build Department Management Module for Employees :
Navigate to https://start.spring.io/
Create Spring boot skeleton project and add dependencies related to Spring data jpa, spring web and thymeleaf as shown in the below screenshot :

<img width="532" alt="Screenshot 2025-02-18 at 10 22 22 AM" src="https://github.com/user-attachments/assets/4634238c-123a-4ef4-ad3d-5097b8aa9187" />

Once the peoject is created, we can import it in IDE of our choice :

Lets create Department Controller for CRUD operations :

<img width="931" alt="Screenshot 2025-02-18 at 10 23 56 AM" src="https://github.com/user-attachments/assets/b9b2cbfb-ee82-40f7-a994-c75ad9420d9b" />

We will similarly create Service and Repository Classes as shown in the below screenshot :

<img width="747" alt="Screenshot 2025-02-18 at 10 24 35 AM" src="https://github.com/user-attachments/assets/6a51bef9-7a53-4098-8fb1-a28c350b4a2b" />

<img width="669" alt="Screenshot 2025-02-18 at 10 24 41 AM" src="https://github.com/user-attachments/assets/e325d86e-3724-4366-9bce-b84e7798752d" />

Lets now create thymeleaf templates for the presentation layer . Full details are present in the code repository.

One important thing is application.properties, where we will create context path. This will help us in setting up path based routing later in the Ingress.

<img width="671" alt="Screenshot 2025-02-18 at 10 26 55 AM" src="https://github.com/user-attachments/assets/451d946c-6956-4cf0-9bc8-431f6b9c9e54" />

Finally we will create docker file to create image for our microservice

<img width="504" alt="Screenshot 2025-02-18 at 10 28 26 AM" src="https://github.com/user-attachments/assets/d8544eb8-dd00-463a-905b-40121ea3b0b3" />

Now, do the maven build using mvn clean install. This will build docker image.

## Step 2 : Store the image in Amazon ECR :

Navigate to AWS console and go to Amazon ECR and click Create Repository and enter the name of the repository as Department and Click Create.

<img width="1742" alt="Screenshot 2025-02-18 at 11 06 36 AM" src="https://github.com/user-attachments/assets/bde5a41b-7554-49fb-9f74-fdcd0baec6cc" />

Once Repository is created, we can now click on View push commands and execute commands for our operating system.

<img width="1548" alt="Screenshot 2025-02-18 at 11 08 11 AM" src="https://github.com/user-attachments/assets/b5231486-1deb-4fe5-a805-a8f6cc0211ea" />


<img width="1150" alt="Screenshot 2025-02-18 at 11 07 29 AM" src="https://github.com/user-attachments/assets/48a6255b-0d29-4d2c-9481-38d8dbaee034" />







